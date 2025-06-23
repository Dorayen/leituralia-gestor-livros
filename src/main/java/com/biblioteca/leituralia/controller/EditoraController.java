package com.biblioteca.leituralia.controller;

import com.biblioteca.leituralia.dto.EditoraDtoRequest;
import com.biblioteca.leituralia.dto.EditoraDtoResponse;
import com.biblioteca.leituralia.service.EditoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
@RequiredArgsConstructor
public class EditoraController {
    private final EditoraService service;

    @PostMapping
    public ResponseEntity<EditoraDtoResponse> cadastrar(@RequestBody EditoraDtoRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dtoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraDtoResponse> exibirEditoraPorID(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarEditoraPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EditoraDtoResponse>> listarEditoras(){
        return ResponseEntity.ok(service.listarTodos());
    }
}
