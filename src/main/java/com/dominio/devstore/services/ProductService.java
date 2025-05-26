package com.dominio.devstore.services;

import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

public interface ProductService {

    EntityModel<ProductDto> findById(Integer id);

    Page<EntityModel<ProductDto>> findAll(Pageable pageable);

    EntityModel<ProductDto> insert(ProductDto dto);

    EntityModel<ProductDto> update(Integer id, ProductDto dto);

    EntityModel<?> delete(Integer id);
}
