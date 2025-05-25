package com.dominio.devstore.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import com.dominio.devstore.services.TokenService;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.dominio.devstore.entities.CustomUserDetails;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import com.dominio.devstore.exceptions.InvalidTokenException;
import com.dominio.devstore.exceptions.TokenGenerationException;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.LocalDateTime;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${devstore.api.token.secret}")
    private String secret;

    @Override
    public String generateToken(CustomUserDetails customUserDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("DevStore API")
                    .withSubject(customUserDetails.getUsername())
                    .withClaim("role", customUserDetails.getAuthorities().toString())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("Error while generating token", exception);
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("DevStore API").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Invalid or expired token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC);
    }
}
