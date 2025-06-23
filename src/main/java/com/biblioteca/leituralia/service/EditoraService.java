package com.biblioteca.leituralia.service;

import com.biblioteca.leituralia.dto.EditoraDtoRequest;
import com.biblioteca.leituralia.dto.EditoraDtoResponse;
import com.biblioteca.leituralia.entity.Editora;
import com.biblioteca.leituralia.exception.EditoraJaCadastradaException;
import com.biblioteca.leituralia.repository.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditoraService {
    private final EditoraRepository repository;

    public EditoraDtoResponse salvar(EditoraDtoRequest dto) {
        if (dto.getId() != null && repository.existsById(dto.getId())) {
            throw new EditoraJaCadastradaException("Editora já foi cadastrada");
        }
        Editora editora = Editora.builder()
                .nome(dto.getNome())
                .cidade(dto.getCidade())
                .build();
        Editora editoraCadastrada = repository.save(editora);
        return toDtoResponse(editoraCadastrada);
    }

    private EditoraDtoResponse toDtoResponse(Editora editoraCadastrada) {
        return EditoraDtoResponse.builder()
                .editoraId(editoraCadastrada.getEditoraId())
                .nome(editoraCadastrada.getNome())
                .cidade(editoraCadastrada.getCidade())
                .build();
    }
}
