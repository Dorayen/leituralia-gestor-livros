package com.biblioteca.leituralia;

import com.biblioteca.leituralia.dto.LivroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leituralia")
public class LeituraliaController {
    private final LeituraliaService service;

    public LeituraliaController(LeituraliaService service) {
        this.service = service;
    }

    @PostMapping
    private ResponseEntity<LivroDto> registraLivro(@RequestBody LivroDto dto) {
        LivroDto livroCadastrado = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }
}
