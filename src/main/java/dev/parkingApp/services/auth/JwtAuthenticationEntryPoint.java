package dev.parkingApp.services.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String errorMessage = authException.getMessage();

        if (authException instanceof BadCredentialsException) {
            log.warn("Invalid credentials");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid credentials");
        }

        else if (authException instanceof InsufficientAuthenticationException) {
            log.warn("Authentication required");
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Authentication required");
        }

        else {
            log.warn("Authentication failed: {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: " + authException.getMessage());
        }
    }
}
