package com.biblioteca.leituralia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long editoraId;
    private String nome;
    private String cidade;
    @OneToMany(mappedBy = "editora")
    private List<Livro> livros;
}
