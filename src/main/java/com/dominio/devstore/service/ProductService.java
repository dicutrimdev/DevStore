package com.dominio.devstore.service;

import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto findById(Integer id);

    Page<ProductDto> findAll(Pageable pageable);
}
