package com.erp.librarymanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/*
 * Author: Rajib Kumer Ghosh
 */

@Component
public class JwtUtil {

    // TODO: Use the same secret as Auth Service
    private static final String SECRET_KEY = "YourSecretKeyForJWTSigningMustBeSecureAndLongEnough123!";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject(); // subject usually holds username
    }

    public boolean isTokenValid(String token) {
        Claims claims = extractAllClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
