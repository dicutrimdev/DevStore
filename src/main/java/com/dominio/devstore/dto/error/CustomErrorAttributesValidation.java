package com.dominio.devstore.dto.error;

import lombok.Getter;

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;

@Getter
public class CustomErrorAttributesValidation extends CustomErrorAttributes {

    private final List<MethodArgumentNotValidMessages> errorMessages = new ArrayList<>();

    public CustomErrorAttributesValidation(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addErrorMessage(String attribute, String message) {
        errorMessages.add(new MethodArgumentNotValidMessages(attribute, message));
    }
}
