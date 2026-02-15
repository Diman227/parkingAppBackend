package dev.parkingApp.services.auth;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.exceptions.TokenException;
import dev.parkingApp.exceptions.ValidationTokenException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Lazy
    private final AuthUserDetailsService authUserDetailsService;

    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
        throws ServletException, IOException {

        final String token = getTokenFromHeader(request);
        Claims claims = null;

        if (token != null) {
            try {
                claims = tokenManager.validateAccessToken(token);
            }
            catch (TokenException ex) {
                log.error("Token Exception: {}", ex.getMessage());
                // todo тут надо уже ответ бы вернуть
            }
            if( claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                final AuthUser userDetails = authUserDetailsService.loadUserByUsername(claims.getSubject());
                final UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
