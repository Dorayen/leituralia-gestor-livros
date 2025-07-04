package com.biblioteca.leituralia.service;

import com.biblioteca.leituralia.dto.EditoraDtoRequest;
import com.biblioteca.leituralia.dto.EditoraDtoResponse;
import com.biblioteca.leituralia.entity.Editora;
import com.biblioteca.leituralia.exception.EditoraJaCadastradaException;
import com.biblioteca.leituralia.exception.EditoraNaoEncontradaException;
import com.biblioteca.leituralia.mapper.EditoraMapper;
import com.biblioteca.leituralia.repository.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EditoraService {
    private final EditoraRepository repository;
    private final EditoraMapper mapper;

    public EditoraDtoResponse salvar(EditoraDtoRequest dto) {
        if (dto.getNome() != null && repository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new EditoraJaCadastradaException("Editora já foi cadastrada");
        }
        Editora editora = mapper.toEntity(dto);
        Editora editoraCadastrada = repository.save(editora);
        return toDtoResponse(editoraCadastrada);
    }

    private EditoraDtoResponse toDtoResponse(Editora editoraCadastrada) {
        return mapper.toDtoResponse(editoraCadastrada);
    }

    public EditoraDtoResponse buscarEditoraPorId(Long id) {
        Editora editoraEncontrada = repository.findById(id).orElseThrow(()->
        new EditoraNaoEncontradaException("Editora não encontrada"));
        return toDtoResponse(editoraEncontrada);
    }

    public List<EditoraDtoResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDtoResponse)
                .collect(Collectors.toList());
    }

    public EditoraDtoResponse atualizarEditora(Long id, EditoraDtoRequest dtoRequest) {
        Editora existente = repository.findById(id)
                .orElseThrow(() -> new EditoraNaoEncontradaException("Editora não encontrada"));

        existente.setNome(dtoRequest.getNome());
        existente.setCidade(dtoRequest.getCidade());

        Editora atualizada = repository.save(existente);
        return toDtoResponse(atualizada);
    }

    public void deletarEditora(Long id) {
        repository.deleteById(id);
    }
}
