package com.biblioteca.leituralia.service;

import com.biblioteca.leituralia.dto.LivroDtoRequest;
import com.biblioteca.leituralia.dto.LivroDtoResponse;
import com.biblioteca.leituralia.entity.Editora;
import com.biblioteca.leituralia.entity.Livro;
import com.biblioteca.leituralia.exception.EditoraNaoEncontradaException;
import com.biblioteca.leituralia.exception.LivroJaCadastradoException;
import com.biblioteca.leituralia.exception.LivroNaoEcontradoException;
import com.biblioteca.leituralia.mapper.LivroMapper;
import com.biblioteca.leituralia.repository.EditoraRepository;
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
    private final EditoraRepository editoraRepository;
    private final LivroMapper mapper;


    public LivroDtoResponse salvar(LivroDtoRequest dtoRequest) {
        if (dtoRequest.getId() != null && livroRepository.existsByTituloIgnoreCaseAndAutorIgnoreCase(dtoRequest.getTitulo(), dtoRequest.getAutor())) {
            throw new LivroJaCadastradoException("Livro já foi cadastrado");
        }

        Editora editora = editoraRepository.findFirstByNome(dtoRequest.getEditora().getNome())
                .orElseThrow(() -> new EditoraNaoEncontradaException("Editora não encontrada"));

        Livro salvo = mapper.toEntity(dtoRequest);
        salvo.setEditora(editora);
        return mapper.toDtoResponse(livroRepository.save(salvo));
    }

    private LivroDtoResponse toDtoResponse(Livro livro) {
        return mapper.toDtoResponse(livro);
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
        Livro existente = livroRepository.findByTituloIgnoreCaseAndAutorIgnoreCase(dtoRequest.getTitulo(), dtoRequest.getAutor())
                .orElseThrow(() -> new LivroNaoEcontradoException("Livro não encontrado com esse título e autor"));

        Editora editora = editoraRepository.findFirstByNome(dtoRequest.getEditora().getNome())
                .orElseThrow(() -> new EditoraNaoEncontradaException("Editora não encontrada"));

        existente.setTitulo(dtoRequest.getTitulo());
        existente.setAutor(dtoRequest.getAutor());
        existente.setCategoria(dtoRequest.getCategoria());
        existente.setAnoPublicacao(dtoRequest.getAnoPublicacao());
        existente.setEditora(editora);
        existente.setDataAtualizacao(LocalDateTime.now());

        Livro atualizado = livroRepository.save(existente);

        return toDtoResponse(atualizado);
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }

}
