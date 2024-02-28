package com.br.joaoptgaino.jwtauthlearning.service;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request.ProductFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request.ProductParamsDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.response.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO create(ProductFormDTO data);

    Page<ProductDTO> findAll(Pageable pageable, ProductParamsDTO paramsDTO);

    ProductDTO findOne(Long id);

    void update(Long id, ProductFormDTO data);

    void delete(Long id);
}
