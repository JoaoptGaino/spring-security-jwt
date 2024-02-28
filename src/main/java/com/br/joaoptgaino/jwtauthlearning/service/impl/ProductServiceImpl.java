package com.br.joaoptgaino.jwtauthlearning.service.impl;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request.ProductFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request.ProductParamsDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.response.ProductDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.entities.ProductEntity;
import com.br.joaoptgaino.jwtauthlearning.repositories.ProductRepository;
import com.br.joaoptgaino.jwtauthlearning.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public ProductDTO create(ProductFormDTO data) {
        ProductEntity product = new ProductEntity(data.name(), data.price());
        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable, ProductParamsDTO paramsDTO) {
        List<ProductDTO> products = productRepository.findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
        return new PageImpl<>(products);
    }

    @Override
    public ProductDTO findOne(Long id) {
        return null;
    }

    @Override
    public void update(Long id, ProductFormDTO data) {

    }

    @Override
    public void delete(Long id) {

    }
}
