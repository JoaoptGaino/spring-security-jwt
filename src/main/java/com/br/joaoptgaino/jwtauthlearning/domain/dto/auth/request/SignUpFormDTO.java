package com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request;

import jakarta.validation.constraints.NotBlank;

public record SignUpFormDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
