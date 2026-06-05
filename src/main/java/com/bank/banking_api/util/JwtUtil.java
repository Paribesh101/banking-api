package com.bank.banking_api.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_MS = 86400000; // 24 hours

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // stores the user's email inside the token as the main identifier
                .setIssuedAt(new Date()) // stamps when the token was created
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // stamps when it expires (now + 24 hours)
                .signWith(key) // signs the token with your secret key so it can't be tampered with
                .compact(); // converts everything into the final token string you see
    }
    // takes a token, verifies the signature, and pulls out the email that was stored inside it
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    // tries to parse the token. If it's valid, it returns true.
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}