package dev.parkingApp.services.auth;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.entities.*;
import dev.parkingApp.exceptions.UserNotFoundException;
import dev.parkingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @NotNull
    @Override
    public AuthUser loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
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

}
