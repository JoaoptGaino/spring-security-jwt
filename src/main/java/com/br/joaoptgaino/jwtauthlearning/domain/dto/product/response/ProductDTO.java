package com.br.joaoptgaino.jwtauthlearning.domain.dto.product.response;

import com.br.joaoptgaino.jwtauthlearning.domain.entities.ProductEntity;

public record ProductDTO(Long id, String name, Long price) {
    public ProductDTO(ProductEntity product) {
        this(product.getId(), product.getName(), product.getPrice());
    }
}
