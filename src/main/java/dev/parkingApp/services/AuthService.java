package dev.parkingApp.services;

import dev.parkingApp.dtos.auth.*;
import dev.parkingApp.dtos.base.NewUserBaseDTO;
import dev.parkingApp.dtos.kafka.UserMessage;
import dev.parkingApp.entities.RefreshTokenEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.*;
import dev.parkingApp.mappers.UserMapper;
import dev.parkingApp.repositories.RefreshTokenRepository;
import dev.parkingApp.repositories.UserRepository;
import dev.parkingApp.services.auth.AuthUserDetailsService;
import dev.parkingApp.services.auth.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserDetailsService authUserDetailsService;
    private final SpotService spotService;

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final UserMapper userMapper;

    @Transactional
    public TokenResponse authenticateUser(LogInRequest logInRequest) {

        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInRequest.getPhoneNumber(),
                            logInRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Passed credentials aren't valid!");
        }

        final AuthUser userDetails = (AuthUser) auth.getPrincipal();
        final Long credentialsId = userDetails.getCredentialsId();
        final String accessToken = tokenManager.generateAccessToken(userDetails);

        final Optional<RefreshTokenEntity> existingRefreshToken = refreshTokenRepository.findTokenByCredentialsId(credentialsId);

        if (existingRefreshToken.isPresent()) {
            String refreshToken = existingRefreshToken.get().getRefreshToken();
            try {
                tokenManager.validateRefreshToken(refreshToken);
                return new TokenResponse(
                        accessToken,
                        refreshToken);
            }
            catch (ValidationTokenException | ExpiredTokenException | InvalidTokenException ex) {
                Long id = existingRefreshToken.get().getId();
                refreshTokenRepository.deleteById(id);
                refreshTokenRepository.flush();
                log.warn("Validation of refresh token is unsuccessful! Creating new refresh token.");
            }
        }

        String refreshToken = tokenManager.generateRefreshToken(userDetails);

        refreshTokenRepository.save(RefreshTokenEntity.builder()
                .refreshToken(refreshToken)
                .credentialsId(credentialsId)
                .build());

        return new TokenResponse(
                accessToken,
                refreshToken);
    }

    @Transactional
    public void registerUser(NewUserBaseDTO userDTO) {

        final UserEntity user = userMapper.createUserFromBaseDTO(userDTO);

        if (userDTO instanceof UserMessage) {
            user.setExternalId(userDTO.getId());
        }

        userRepository.save(user);

        if(user.getExternalId() != null){
            spotService.updateUnownedSpots(user.getId(), user.getExternalId());
        }

        log.info("Saved user is - {}", user.toString());
    }

    public TokenResponse refreshTokens(RefreshTokenRequest refreshTokenRequest, boolean refreshBothTokens)  {

        final Claims refreshClaims = tokenManager.validateRefreshToken(refreshTokenRequest.getRefreshToken());

        if (refreshClaims == null) throw new InvalidRefreshTokenException("Invalid Refresh token!");

        final Long credentialsId = refreshClaims.get("credentialsId", Long.class);

        final RefreshTokenEntity refreshToken = refreshTokenRepository.findTokenByCredentialsId(credentialsId).orElseThrow(
                () -> new RefreshTokenNotFoundException("Expected refresh token wasn't found")
        );

        if (!refreshToken.getRefreshToken().equals(refreshTokenRequest.getRefreshToken())) {
            throw new InvalidRefreshTokenException("Passed refresh token isn't valid");
        }

        final AuthUser authUser = authUserDetailsService.loadUserByUsername(refreshClaims.getSubject());
        final String accessToken = tokenManager.generateAccessToken(authUser);

        if (refreshBothTokens) {
            refreshToken.setRefreshToken(tokenManager.generateRefreshToken(authUser));
            refreshTokenRepository.save(refreshToken);

            return new TokenResponse(
                    accessToken,
                    refreshToken.getRefreshToken()
            );
        }
        return new TokenResponse(
                accessToken,
                null
        );
    }

}
