package dev.parkingApp.controllers;

import dev.parkingApp.dtos.UserDTO;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.dtos.auth.TokenRequest;
import dev.parkingApp.dtos.auth.TokenResponse;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.mappers.UserMapper;
import dev.parkingApp.repositories.CredentialsRepository;
import dev.parkingApp.repositories.UserRepository;
import dev.parkingApp.services.auth.AuthUserDetailsService;
import dev.parkingApp.services.auth.TokenManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("*api/auth/")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final CredentialsRepository credentialsRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping(value = "login")
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenRequest.getPhoneNumber(), tokenRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final AuthUser userDetails = userDetailsService.loadUserByUsername(tokenRequest.getPhoneNumber());
        return new TokenResponse(tokenManager.generateJwtToken(userDetails));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "register")
    public void addUser(@RequestBody SignInRequest request) {

        userDetailsService.createUser(request);
    }
}
