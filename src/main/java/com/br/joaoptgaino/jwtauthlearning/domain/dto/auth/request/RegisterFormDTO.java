package com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request;

import com.br.joaoptgaino.jwtauthlearning.domain.enums.UserRole;

public record RegisterFormDTO(String username, String password, UserRole role) {
}
