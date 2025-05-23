package com.dominio.devstore.mapper;

import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
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

    public static Product fromDtoToEntity(ProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .img_url(dto.getImg_url())
                .build();
    }

    public static void updateEntityFromDto(Product product, ProductDto dto) {
        if (dto.getName() != null)
            product.setName(dto.getName());

        if (dto.getDescription() != null)
            product.setDescription(dto.getDescription());

        if (dto.getPrice() != null)
            product.setPrice(dto.getPrice());

        if (dto.getImg_url() != null)
            product.setImg_url(dto.getImg_url());
    }

    public static Page<ProductDto> fromEntityListToDtoList(Page<Product> products) {
        return products.map(ProductMapper::fromEntityToDto);
    }
}
