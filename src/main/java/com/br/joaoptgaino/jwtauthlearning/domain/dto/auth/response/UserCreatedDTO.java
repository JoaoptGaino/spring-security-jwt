package com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response;

import com.br.joaoptgaino.jwtauthlearning.domain.entities.UserEntity;

public record UserCreatedDTO(Long id, String username) {
    public UserCreatedDTO(UserEntity user) {
        this(user.getId(), user.getUsername());
    }
}
