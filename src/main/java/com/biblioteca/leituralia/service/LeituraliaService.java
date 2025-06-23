package com.biblioteca.leituralia.service;

import com.biblioteca.leituralia.dto.LivroDtoRequest;
import com.biblioteca.leituralia.dto.LivroDtoResponse;
import com.biblioteca.leituralia.entity.Livro;
import com.biblioteca.leituralia.exception.LivroJaCadastradoException;
import com.biblioteca.leituralia.exception.LivroNaoEcontradoException;
import com.biblioteca.leituralia.repository.LeituraliaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeituraliaService {
    private final LeituraliaRepository livroRepository;


    public LivroDtoResponse salvar(LivroDtoRequest dtoRequest) {
        if (dtoRequest.getId() != null && livroRepository.existsById(dtoRequest.getId())) {
            throw new LivroJaCadastradoException("Livro já foi cadastrado");
        }

        Livro livro = Livro.builder()
                .titulo(dtoRequest.getTitulo())
                .autor(dtoRequest.getAutor())
                .categoria(dtoRequest.getCategoria())
                .anoPublicacao(dtoRequest.getAnoPublicacao())
                .dataCadastro(LocalDateTime.now())
                .build();
        Livro salvo = livroRepository.save(livro);
        return toDtoResponse(salvo);
    }

    private LivroDtoResponse toDtoResponse(Livro livro) {
        return LivroDtoResponse.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .categoria(livro.getCategoria())
                .anoPublicacao(livro.getAnoPublicacao())
                .build();
    }

    public LivroDtoResponse buscarLivroPorID(Long id) {
        Livro livroEncontrado = livroRepository.findById(id).orElseThrow(() -> new LivroNaoEcontradoException("livro não encontrado"));
        return toDtoResponse(livroEncontrado);
    }

    public List<LivroDtoResponse> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::toDtoResponse)
                .collect(Collectors.toList());
    }

    public LivroDtoResponse atualizarLivro(Long id, LivroDtoRequest dtoRequest) {
        Livro existente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEcontradoException("Livro não encontrado"));


        existente.setTitulo(dtoRequest.getTitulo());
        existente.setAutor(dtoRequest.getAutor());
        existente.setCategoria(dtoRequest.getCategoria());
        existente.setAnoPublicacao(dtoRequest.getAnoPublicacao());
        existente.setDataAtualizacao(LocalDateTime.now());
        Livro atualizado = livroRepository.save(existente);

        return toDtoResponse(atualizado);
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }

}
