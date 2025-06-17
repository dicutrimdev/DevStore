package com.dominio.devstore.mapper;

import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.entities.UserCredentials;

public class UserMapper {

    public static UserCredentials toUser(RequestRegisterUser request) {
        return UserCredentials.builder()
                .username(request.email())
                .password(request.password())
                .role(request.role())
                .build();
    }
}
