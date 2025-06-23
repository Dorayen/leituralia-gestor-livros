package com.biblioteca.leituralia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditoraDtoResponse {
    private Long editoraId;
    private String nome;
    private String cidade;
}

