package com.dominio.devstore.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.dominio.devstore.dto.ProductDto;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.dominio.devstore.services.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Tag(name = "Product")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter por id", description = "Obtenção por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<EntityModel<ProductDto>> findById(@PathVariable Integer id) {
        var productDto = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @GetMapping
    @Operation(summary = "Obter listagem", description = "Obtenção de lista de produtos")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Produtos obtidos com sucesso"))
    public ResponseEntity<Page<EntityModel<ProductDto>>> findAll(Pageable pageable) {
        var productDto = productService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @PostMapping
    @Operation(summary = "Inserir", description = "Inserção de produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "409", description = "Produto já cadastrado"),
            @ApiResponse(responseCode = "201", description = "Cadastro feito com sucesso")
    })
    public ResponseEntity<EntityModel<ProductDto>> insert(@Valid @RequestBody ProductDto dto) {
        var productDto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(Objects.requireNonNull(productDto.getContent()).getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }


    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar", description = "Atualização por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "200", description = "Atualização feito com sucesso")}
    )
    public ResponseEntity<EntityModel<ProductDto>> update(@PathVariable Integer id,
                                                          @Valid @RequestBody ProductDto dto) {
        var updatedProduct = productService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar", description = "Deleção por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")}
    )
    public ResponseEntity<EntityModel<?>> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
