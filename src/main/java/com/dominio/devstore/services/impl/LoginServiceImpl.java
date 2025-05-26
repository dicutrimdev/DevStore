package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.EntityModel;
import com.dominio.devstore.dto.LoginRequestDto;
import com.dominio.devstore.dto.LoginResponseDto;
import com.dominio.devstore.services.TokenService;
import com.dominio.devstore.services.LoginService;
import com.dominio.devstore.entities.UserCredentials;
import com.dominio.devstore.controllers.ProductController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public EntityModel<LoginResponseDto> login(LoginRequestDto request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password())
        );
        var user = (UserCredentials) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        var response = new LoginResponseDto(user.getUsername(), token);
        return EntityModel.of(response).add(linkTo(methodOn(ProductController.class)
                .findAll(null)).withRel("Find All"));
    }
}
