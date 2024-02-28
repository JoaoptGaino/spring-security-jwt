package com.br.joaoptgaino.jwtauthlearning.infra.security.service;

import com.br.joaoptgaino.jwtauthlearning.domain.entities.UserEntity;

public interface TokenService {

    String generateToken(UserEntity user);

    String validateToken(String token);
}
