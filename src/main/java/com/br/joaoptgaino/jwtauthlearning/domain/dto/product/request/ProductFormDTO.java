package com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductFormDTO(
        @NotBlank
        String name,
        @NotNull
        Long price
) {
}
