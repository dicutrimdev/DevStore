package com.dominio.devstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.dominio.devstore.services.FakeApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/products")
public class FakeApiController {

    private final FakeApiService fakeApiService;

    @PostMapping(value = "/import")
    public ResponseEntity<String> importProductsFromFakeApi() {
        try {
            fakeApiService.fromFakeApiToDevStore();
            return ResponseEntity.status(HttpStatus.OK).body("Products imported successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing products" + e.getMessage());
        }
    }
}
