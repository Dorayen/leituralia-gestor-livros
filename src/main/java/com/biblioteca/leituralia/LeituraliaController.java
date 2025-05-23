package com.biblioteca.leituralia;

import com.biblioteca.leituralia.dto.LivroDto;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public LeituraliaController(LeituraliaService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    private ResponseEntity<LivroDto> registraLivro(@RequestBody LivroDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
        Livro salvo = service.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(salvo, LivroDto.class));
    }

}
