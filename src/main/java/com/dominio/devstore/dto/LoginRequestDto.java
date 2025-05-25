package com.dominio.devstore.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
