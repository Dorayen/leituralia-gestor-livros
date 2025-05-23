package com.biblioteca.leituralia;

import com.biblioteca.leituralia.dto.LivroDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto>  exibirLivroPorId(@PathVariable Long id) {
        Livro livro = service.buscarLivroPorID(id);
        LivroDto dto = modelMapper.map(livro, LivroDto.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> listarLivros() {
        List<LivroDto> dtos=  service.listarTodos()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LivroDto>  atualizarLivro(@PathVariable Long id, @RequestBody LivroDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
        Livro livroatualizado = service.atualizarLivro(id, livro);
        LivroDto resposta = modelMapper.map(livroatualizado, LivroDto.class);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping({"/{id}"})
    public void deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
    }

}
