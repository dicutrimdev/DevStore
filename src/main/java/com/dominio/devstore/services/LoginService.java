package com.dominio.devstore.services;

import com.dominio.devstore.dto.LoginRequestDto;
import com.dominio.devstore.dto.LoginResponseDto;
import org.springframework.hateoas.EntityModel;

public interface LoginService {
    EntityModel<LoginResponseDto> login(LoginRequestDto request);
}
