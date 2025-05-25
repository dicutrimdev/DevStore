package com.dominio.devstore.exceptions;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
