package dev.parkingApp.controllers;

import dev.parkingApp.dtos.auth.*;
import dev.parkingApp.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("*api/auth/")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> createToken(
            @RequestBody @Valid LogInRequest logInRequest) throws Exception {

        return new ResponseEntity<>(authService.authenticateUser(logInRequest), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<String> createUser(
            @RequestBody @Valid SignInRequest request) {
        authService.registerUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "refresh")
    public ResponseEntity<TokenResponse> refreshToken(
            @RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return new ResponseEntity<>(authService.refreshTokens(refreshTokenRequest, true), HttpStatus.OK);
    }
}
