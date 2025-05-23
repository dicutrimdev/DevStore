package com.dominio.devstore.service.impl;

import lombok.RequiredArgsConstructor;
import com.dominio.devstore.dto.ProductDto;
import com.dominio.devstore.entities.Product;
import org.springframework.stereotype.Service;
import com.dominio.devstore.mapper.ProductMapper;
import com.dominio.devstore.service.ProductService;
import com.dominio.devstore.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import com.dominio.devstore.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Integer id) {
        var product = findProductByIdOrThrow(id);
        return ProductMapper.fromEntityToDto(product);
    }

    private Product findProductByIdOrThrow(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("Product id must not be null");
        if (id <= 0)
            throw new IllegalArgumentException("Product id must be greater than zero");

        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product id: " + id + " not found")
        );
    }
}
