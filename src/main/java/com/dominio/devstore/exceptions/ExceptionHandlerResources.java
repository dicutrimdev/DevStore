package com.dominio.devstore.exceptions;

import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.dominio.devstore.dto.CustomErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerResources {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorAttributes> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(Instant.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorAttributes);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorAttributes> databaseException(DatabaseException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(Instant.now(), HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customErrorAttributes);
    }
}
