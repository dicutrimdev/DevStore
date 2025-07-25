package com.dominio.devstore.exceptions;

import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import com.dominio.devstore.dto.error.CustomErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.dominio.devstore.dto.error.CustomErrorAttributesValidation;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerResources {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<CustomErrorAttributes> invalidTokenException(InvalidTokenException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customErrorAttributes);
    }

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<CustomErrorAttributes> tokenGenerationException(TokenGenerationException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorAttributes);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorAttributes> badCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "Email ou senha inválidos",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customErrorAttributes);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorAttributes> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorAttributes);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomErrorAttributes> usernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorAttributes);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<CustomErrorAttributes> emailAlreadyUsedException(EmailAlreadyUsedException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorAttributes);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorAttributes> databaseException(DatabaseException ex, HttpServletRequest request) {
        var customErrorAttributes = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customErrorAttributes);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorAttributes> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var customErrorAttributesValidation = new CustomErrorAttributesValidation(
                Instant.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Invalid data",
                request.getRequestURI()
        );
        for (FieldError f : ex.getBindingResult().getFieldErrors()) {
            customErrorAttributesValidation.addErrorMessage(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customErrorAttributesValidation);
    }
}
