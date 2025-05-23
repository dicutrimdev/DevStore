package com.dominio.devstore.dto.error;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class CustomErrorAttributes {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
