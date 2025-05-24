package com.dominio.devstore.dto;

import java.time.LocalDate;

public record RequestRegisterUser(
        String name,
        String email,
        String password,
        LocalDate birthDate
) {
}
