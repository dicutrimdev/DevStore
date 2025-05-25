package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dominio.devstore.dto.LoginRequestDto;
import com.dominio.devstore.dto.LoginResponseDto;
import com.dominio.devstore.services.TokenService;
import com.dominio.devstore.services.LoginService;
import com.dominio.devstore.entities.UserCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password())
        );
        var user = (UserCredentials) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        return new LoginResponseDto(user.getUsername(), token);
    }
}
