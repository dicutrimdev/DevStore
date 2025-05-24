package com.dominio.devstore.mapper;

import com.dominio.devstore.entities.User;
import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.entities.enums.UserRole;

public class UserMapper {

    public static User toUser(RequestRegisterUser request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .birthDate(request.birthDate())
                .role(UserRole.USER)
                .build();
    }
}
