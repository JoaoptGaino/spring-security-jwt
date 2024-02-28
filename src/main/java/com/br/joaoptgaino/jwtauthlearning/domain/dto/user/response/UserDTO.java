package com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response;

import com.br.joaoptgaino.jwtauthlearning.domain.entities.UserEntity;

public record UserDTO(Long id, String username, String email) {
    public UserDTO(UserEntity user) {
        this(user.getId(), user.getUsername(), user.getUsername());
    }
}
