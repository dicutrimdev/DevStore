package com.dominio.devstore.service;

import com.dominio.devstore.dto.ProductDto;

public interface ProductService {

    ProductDto findById(Integer id);
}
