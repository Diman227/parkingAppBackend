package dev.parkingApp.controllers;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.dtos.auth.TokenRequest;
import dev.parkingApp.dtos.auth.TokenResponse;
import dev.parkingApp.exceptions.InvalidCredentialsException;
import dev.parkingApp.services.auth.AuthUserDetailsService;
import dev.parkingApp.services.auth.TokenManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("*api/auth/")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class AuthController {

    private final AuthUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> createToken(
            @RequestBody @Valid TokenRequest tokenRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenRequest.getPhoneNumber(), tokenRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Passed credentials aren't valid!");
        }

        final AuthUser userDetails = userDetailsService.loadUserByUsername(tokenRequest.getPhoneNumber());
        TokenResponse tokenResponse = new TokenResponse(tokenManager.generateJwtToken(userDetails));
        return new ResponseEntity<>(tokenResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "register")
    public ResponseEntity<String> createUser(
            @RequestBody @Valid SignInRequest request) {
        userDetailsService.createUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
