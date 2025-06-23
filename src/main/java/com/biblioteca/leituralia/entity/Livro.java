package com.biblioteca.leituralia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private String anoPublicacao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;


}
