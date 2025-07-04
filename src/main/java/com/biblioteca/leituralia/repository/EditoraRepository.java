package com.biblioteca.leituralia.repository;

import com.biblioteca.leituralia.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    Optional<Editora> findFirstByNome(String nome);

}