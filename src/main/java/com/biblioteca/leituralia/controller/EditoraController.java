package com.biblioteca.leituralia.controller;

import com.biblioteca.leituralia.dto.EditoraDtoRequest;
import com.biblioteca.leituralia.dto.EditoraDtoResponse;
import com.biblioteca.leituralia.service.EditoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editoras")
@RequiredArgsConstructor
public class EditoraController {
    private final EditoraService service;

    @PostMapping
    public ResponseEntity<EditoraDtoResponse> cadastrar(@RequestBody EditoraDtoRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dtoRequest));
    }
}
