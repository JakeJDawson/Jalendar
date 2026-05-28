package com.jalendar.jalendar_service;

// Import statements.
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

// This is managed by Spring.
@Component
public class JwtUtil {
    // Fetches a secret code, which isn't public, to base keys on.
    @Value("${jwt.secret}")
    private String SECRET;
    // Time after login that causes session to expire.
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour.

    // Convert secret code to cryptographic key to sign and verify tokens.
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate a token to remember the session.
    public String generateToken(String email, Long userId) {
        return Jwts.builder()
            .setSubject(email)
            .claim("userId", userId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /** 
     * Basically tries to return the info inside of a JWT key, which will fail
     * if the key isn't valid.
     */
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    // Gets the email from the JWT key.
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Gets the user ID from the JWT key.
    public Long extractUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    // Just checks if the provided token is valid.
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}