package com.auth.authimplementation.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private final String SECRET = "hsrnjgnsongjajobgjbajbguibuirbiwbeihbtibitwibeuitwuinjfgjnjgdfgbfdt";
    private final long EXPIRATION = 86400000; // 1 day

    public String generateToken(String username) {
        // Implementation for generating JWT token
         return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
    

    public String validateTokenAndGetUsername(String token) {
        // Implementation for validating JWT token and extracting username
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
    //altenative method to extract username

    public String extractUserNameWithClaims(String token) {
        return extractAllClaims(token).getSubject();
    }
    public String validateTokenWithClaims(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }
    public boolean validateTokenBooleanWithClaims(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isTokenExpired(String token) {
        final java.util.Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new java.util.Date());
    }
}
