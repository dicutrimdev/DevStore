package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import com.dominio.devstore.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.dominio.devstore.mapper.ProductMapper;
import com.dominio.devstore.services.ProductService;
import com.dominio.devstore.exceptions.DatabaseException;
import com.dominio.devstore.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        var products = productRepository.findAll(pageable);
        return ProductMapper.fromEntityListToDtoList(products);
    }

    @Override
    @Transactional
    public ProductDto insert(ProductDto dto) {
        var product = ProductMapper.fromDtoToEntity(dto);
        return ProductMapper.fromEntityToDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto update(Integer id, ProductDto dto) {
        var product = findProductByIdOrThrow(id);
        ProductMapper.updateEntityFromDto(product, dto);
        return ProductMapper.fromEntityToDto(productRepository.save(product));
    }

    @Override
    public void delete(Integer id) {
        var product = findProductByIdOrThrow(id);
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Integrity violation fail");
        }
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
