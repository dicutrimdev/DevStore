package com.dominio.devstore.exceptions;

public class InvalidTokenException extends RuntimeException {
  public InvalidTokenException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
