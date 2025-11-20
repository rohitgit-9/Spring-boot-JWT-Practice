package com.example.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {
    private String secret="3kd9sFJH48Kz8d9f983hf9Hf983hf9Hf983hf9Hf9f=";

    public SecretKey getSecretKey() {
      return  Keys.hmacShaKeyFor(this.secret.getBytes());
    }

    public String generateToken(String username){
       return  Jwts.builder().subject(username)
                .claims(new HashMap<String,Object>())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey()).compact();
    }

    public Claims parseToken(String token){
       return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    }

    public String extractUsernameFromToken(String token){
        return parseToken(token).getSubject();
    }


    public boolean isTokenExpired(String token){
      return  parseToken(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token){
        return !isTokenExpired(token);
    }
}
