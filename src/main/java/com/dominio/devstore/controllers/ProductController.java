package com.dominio.devstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import com.dominio.devstore.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
        var productDto = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findById(Pageable pageable) {
        var productDto = productService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }
}
