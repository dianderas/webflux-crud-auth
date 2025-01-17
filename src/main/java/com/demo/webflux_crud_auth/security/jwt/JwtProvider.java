package com.demo.webflux_crud_auth.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();

        return Jwts.builder()
                .claims(Map.of(
                        "sub", userDetails.getUsername(),
                        "roles", userDetails.getAuthorities(),
                        "iat", Date.from(now),
                        "exp", Date.from(now.plusMillis(expiration))
                ))
                .signWith(getKey(secretKey))
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("token expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("unsupported token: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("malformed token: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("invalid signature: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("illegal argument: {}", e.getMessage());
        }
        return false;
    }

    private SecretKey getKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
