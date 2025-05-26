package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import com.dominio.devstore.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;
import com.dominio.devstore.dto.MessageResponse;
import com.dominio.devstore.mapper.ProductMapper;
import com.dominio.devstore.services.ProductService;
import com.dominio.devstore.exceptions.DatabaseException;
import com.dominio.devstore.controllers.ProductController;
import com.dominio.devstore.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import com.dominio.devstore.exceptions.ProductNotFoundException;
import com.dominio.devstore.exceptions.ResourceNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public EntityModel<ProductDto> findById(Integer id) {
        var product = findProductByIdOrThrow(id);
        var productDto = ProductMapper.fromEntityToDto(product);
        return EntityModel.of(productDto).add(linkTo(methodOn(ProductController.class)
                .findAll(null)).withRel("Find All"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EntityModel<ProductDto>> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> {
            ProductDto dto = ProductMapper.fromEntityToDto(product);
            return EntityModel.of(dto, linkTo(methodOn(ProductController.class)
                    .findById(dto.getId())).withSelfRel()
            );
        });
    }

    @Override
    @Transactional
    public EntityModel<ProductDto> insert(ProductDto dto) {
        if (productRepository.existsByName(dto.getName())) throw new ProductNotFoundException(
                "A product with this name already exists");
        var product = ProductMapper.fromDtoToEntity(dto);
        var productDto = ProductMapper.fromEntityToDto(productRepository.save(product));
        return EntityModel.of(productDto)
                .add(linkTo(methodOn(ProductController.class).findAll(null)).withRel("Find All"))
                .add(linkTo(methodOn(ProductController.class).findById(productDto.getId())).withRel("Find By Id"));
    }

    @Override
    @Transactional
    public EntityModel<ProductDto> update(Integer id, ProductDto dto) {
        var product = findProductByIdOrThrow(id);
        ProductMapper.updateEntityFromDto(product, dto);
        var atualizedProduct = productRepository.save(product);
        var atualizedProductDto = ProductMapper.fromEntityToDto(atualizedProduct);
        return EntityModel.of(atualizedProductDto).add(linkTo(methodOn(ProductController.class)
                .findById(atualizedProductDto.getId())).withRel("Find By Id"));
    }

    @Override
    public EntityModel<?> delete(Integer id) {
        var product = findProductByIdOrThrow(id);

        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Integrity violation fail");
        }

        var response = new MessageResponse("Deleted successfully");
        return EntityModel.of(response).add(linkTo(methodOn(ProductController.class)
                .findAll(null)).withRel("Find All"));
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
