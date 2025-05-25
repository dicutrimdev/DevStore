package com.dominio.devstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import com.dominio.devstore.dto.LoginRequestDto;
import com.dominio.devstore.dto.LoginResponseDto;
import com.dominio.devstore.services.LoginService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    @Operation(summary = "Login do usuário", description = "Realiza autenticação do usuário com email e senha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(request));
    }
}
