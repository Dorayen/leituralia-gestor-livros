package com.biblioteca.leituralia.controller;

import com.biblioteca.leituralia.dto.LivroDtoRequest;
import com.biblioteca.leituralia.dto.LivroDtoResponse;
import com.biblioteca.leituralia.service.LeituraliaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituralia")
public class LeituraliaController {
    private final LeituraliaService service;

    public LeituraliaController(LeituraliaService service) {
        this.service = service;
    }

    @PostMapping
    private ResponseEntity<LivroDtoResponse> registraLivro(@RequestBody LivroDtoRequest dto) {
        LivroDtoResponse salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDtoResponse> exibirLivroPorId(@PathVariable Long id) {
        LivroDtoResponse livroEncontrado = service.buscarLivroPorID(id);
        return ResponseEntity.ok(livroEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<LivroDtoResponse>> listarLivros() {
        return ResponseEntity.ok(service.listarTodos());
    }
    @PutMapping("/{id}")
    public ResponseEntity<LivroDtoResponse> atualizarLivro(@PathVariable Long id, @RequestBody LivroDtoRequest dto) {
        LivroDtoResponse livroatualizado = service.atualizarLivro(id, dto);
        return ResponseEntity.ok(livroatualizado);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

}
