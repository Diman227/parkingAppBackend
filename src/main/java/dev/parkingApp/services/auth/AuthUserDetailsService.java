package dev.parkingApp.services.auth;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.entities.*;
import dev.parkingApp.exceptions.UserNotFoundException;
import dev.parkingApp.repositories.CredentialsRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final CredentialsRepository credentialsRepository;
    private final UserRepository userRepository;

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserByUsername(username).orElseThrow(
                () -> new UserNotFoundException(String.format("User with name %s not found!", username))
        );

        return AuthUser.builder()
                .phoneNumber(user.getCredentials().getPhoneNumber())
                .password(user.getCredentials().getPassword().getPassword())
                .enabled(true)
                .credentialsId(user.getCredentials().getId())
                .userId(user.getId())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }

    @Transactional
    public void createUser(SignInRequest request) {

        CredentialsEntity credentialsEntity = CredentialsEntity.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(new PasswordEntity(request.getPassword()))
                .build();

        UserEntity user = UserEntity.builder()
                .surname(request.getSurname())
                .name(request.getName())
                .email(request.getEmail())
                .credentials(credentialsEntity)
                .build();

        userRepository.save(user);
    }
}
