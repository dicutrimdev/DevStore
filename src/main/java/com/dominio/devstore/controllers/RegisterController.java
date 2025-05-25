package com.dominio.devstore.controllers;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.services.RegisterService;
import com.dominio.devstore.dto.ResponseRegisteredUser;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Register")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping(value = "/register")
    @Operation(summary = "Registrar novo usuário",
            description = "Cria um novo usuário na base de dados com as informações fornecidas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida ou dados faltando"),
            @ApiResponse(responseCode = "409", description = "Usuário já existente")
    })
    public ResponseEntity<ResponseRegisteredUser> register(@RequestBody RequestRegisterUser request) {
        var response = registerService.register(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
