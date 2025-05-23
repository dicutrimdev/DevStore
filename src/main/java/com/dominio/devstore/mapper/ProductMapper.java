package com.dominio.devstore.mapper;

import com.dominio.devstore.dto.ProductDto;
import com.dominio.devstore.entities.Product;

public class ProductMapper {

    public static ProductDto fromEntityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .img_url(product.getImg_url())
                .build();
    }
}
