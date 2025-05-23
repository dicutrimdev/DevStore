package com.dominio.devstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String img_url;
}
