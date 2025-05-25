package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dominio.devstore.dto.LoginRequestDto;
import com.dominio.devstore.dto.LoginResponseDto;
import com.dominio.devstore.services.LoginService;
import com.dominio.devstore.entities.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        var user = (CustomUserDetails) auth.getPrincipal();
        return new LoginResponseDto(user.getUsername());
    }
}
