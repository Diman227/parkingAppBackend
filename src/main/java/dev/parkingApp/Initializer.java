package dev.parkingApp;

import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.services.auth.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final AuthUserDetailsService authUserDetailsService;

    public void initial() {

        authUserDetailsService.createUser(new SignInRequest("+7 952 235 53 62", "password", "Biba", "Max", "sobaka@mail.ru"));
        authUserDetailsService.createUser(new SignInRequest("+7 920 513 12 94", "password1", "Boba", "Grisha", "sile1@gmail.com"));
        authUserDetailsService.createUser(new SignInRequest("+7 999 777 51 12", "password2", "Bibi", "Roma", "dksdg2@gmail.com"));

        authUserDetailsService.createUser(new SignInRequest("1", "1", "1", "1", "1"));
    }
}
