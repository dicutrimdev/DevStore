package com.dominio.devstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.dominio.devstore.services.ProductService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


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

    @PostMapping
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto dto) {
        var productDto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer id,
                                             @RequestBody ProductDto dto) {
        var updatedProduct = productService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
