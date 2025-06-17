package com.dominio.devstore.dto;

import com.dominio.devstore.entities.enums.UserRole;

import java.time.LocalDate;

public record RequestRegisterUser(
        String name,
        String email,
        String password,
        UserRole role
) {
}
