package dev.parkingApp.services.auth;

import dev.parkingApp.dtos.auth.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager {

    public static final long TOKEN_VALIDITY = 10 * 60 * 60;

    @Value("${token.signing.key}")
    private String jwtSecret;


    public String generateJwtToken(AuthUser authUser) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims().empty().add(claims)
                .and()
                .subject(authUser.getPhoneNumber())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .claim("role", authUser.getAuthorities())
                .signWith(getKey())
                .compact();
    }

    public Boolean validateJwtToken(String token, AuthUser userDetails) {
        final String username = getUsernameFromToken(token);

        final Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getPhoneNumber())) && !isTokenExpired;
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
