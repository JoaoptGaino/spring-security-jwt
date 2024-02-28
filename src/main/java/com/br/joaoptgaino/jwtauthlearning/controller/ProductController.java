package com.br.joaoptgaino.jwtauthlearning.controller;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request.ProductFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.request.ProductParamsDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.product.response.ProductDTO;
import com.br.joaoptgaino.jwtauthlearning.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductFormDTO data) {
        return ResponseEntity.ok(productService.create(data));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) ProductParamsDTO paramsDTO) {
        return ResponseEntity.ok(productService.findAll(pageable, paramsDTO));
    }
}
