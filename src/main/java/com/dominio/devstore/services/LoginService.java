package com.dominio.devstore.services;

import com.dominio.devstore.dto.LoginRequestDto;
import com.dominio.devstore.dto.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto request);
}
