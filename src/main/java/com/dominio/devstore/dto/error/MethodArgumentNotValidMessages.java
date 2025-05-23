package com.dominio.devstore.dto.error;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class MethodArgumentNotValidMessages {

    private String attribute;
    private String message;
}
