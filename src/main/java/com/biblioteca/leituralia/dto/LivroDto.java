package com.biblioteca.leituralia.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {
    @NonNull
    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private String anoPublicacao;


}
