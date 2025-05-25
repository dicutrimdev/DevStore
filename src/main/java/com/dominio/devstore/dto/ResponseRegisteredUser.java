package com.dominio.devstore.dto;

import com.dominio.devstore.entities.enums.UserRole;

public record ResponseRegisteredUser(Integer id, String name, UserRole role) {
}
