package com.dominio.devstore.entities.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String role;
}
