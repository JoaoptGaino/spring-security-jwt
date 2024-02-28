package com.br.joaoptgaino.jwtauthlearning.repositories;

import com.br.joaoptgaino.jwtauthlearning.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
