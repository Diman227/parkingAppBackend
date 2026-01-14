package dev.parkingApp.services.auth;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.entities.*;
import dev.parkingApp.repositories.CredentialsRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final CredentialsRepository credentialsRepository;
    private final UserRepository userRepository;

    //todo метод должен так называться
    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        CredentialsEntity user = credentialsRepository.findByPhoneNumber(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username))
        );

        return AuthUser.builder()
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword().getPassword())
                .enabled(true)
                .credentialsId(user.getId())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }

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
