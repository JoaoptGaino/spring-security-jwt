package com.br.joaoptgaino.jwtauthlearning.infra.security.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.joaoptgaino.jwtauthlearning.domain.entities.UserEntity;
import com.br.joaoptgaino.jwtauthlearning.infra.security.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String ISSUER;


    @Override
    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new JWTCreationException("Error creating token", ex);
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("Token expired or invalid", ex);
        }
    }

    private static Instant getExpiresAt() {
        return LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00"));
    }
}
