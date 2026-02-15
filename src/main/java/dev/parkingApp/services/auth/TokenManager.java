package dev.parkingApp.services.auth;

import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.exceptions.ExpiredTokenException;
import dev.parkingApp.exceptions.InvalidRefreshTokenException;
import dev.parkingApp.exceptions.InvalidTokenException;
import dev.parkingApp.exceptions.ValidationTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenManager {

    @Value("${jwt.access-token.access-key}")
    private String accessSecret;

    @Value("${jwt.access-token.expiration-time}")
    private int accessExpirationTime;

    @Value("${jwt.refresh-token.refresh-key}")
    private String refreshSecret;

    @Value("${jwt.refresh-token.expiration-time}")
    private int refreshExpirationTime;

    public String generateAccessToken(AuthUser authUser) {
        return Jwts
                .builder()
                .subject(authUser.getPhoneNumber())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpirationTime * 1000L))
                .issuer("AUTH-service")
                .claim("role", authUser.getAuthorities())
                .signWith(getKey(accessSecret))
                .compact();
    }

    public String generateRefreshToken(AuthUser authUser) {
        return Jwts
                .builder()
                .subject(authUser.getPhoneNumber())
                .claim("credentialsId", authUser.getCredentialsId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshExpirationTime * 1000L))
                .signWith(getKey(refreshSecret))
                .compact();
    }

    public Claims validateAccessToken(String token) {
        return validateToken(token, accessSecret);
    }

    public Claims validateRefreshToken(String token) {
        return validateToken(token, refreshSecret);
    }

    private Claims validateToken(String token, String secretKey) {

        try {
            return getClaims(token, secretKey);
        }
        catch (ExpiredJwtException ex) {
            log.error("Access token expired: {}", ex.getMessage());
            throw new ExpiredTokenException("Passed token expired!");
        }
        catch (MalformedJwtException ex) {
            log.error("Malformed JWT: {}", ex.getMessage());
            throw new InvalidTokenException("Wrong token's structure!");
        }
        catch (SignatureException ex) {
            log.error("Invalid JWT structure: {}", ex.getMessage());
            throw new InvalidTokenException("Invalid token's signature!");
        }
        catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT: {}", ex.getMessage());
            throw new InvalidTokenException("Unsupported format of token!");
        }
        catch (IllegalArgumentException ex) {
            log.error("JWT claims're empty: {}", ex.getMessage());
            throw new InvalidTokenException("Illegal or inappropriate arguments!");
        }
    }

    private Claims getClaims(String token, String secretKey) {
        return Jwts
                .parser()
                .verifyWith(getKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
