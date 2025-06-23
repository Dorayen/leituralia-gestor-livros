package com.biblioteca.leituralia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDtoResponse {
    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private String anoPublicacao;
    private EditoraDtoResponse editoraDtoResponse;
}
