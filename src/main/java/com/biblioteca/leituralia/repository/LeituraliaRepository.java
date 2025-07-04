package com.biblioteca.leituralia.repository;

import com.biblioteca.leituralia.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeituraliaRepository extends JpaRepository<Livro, Long> {
    boolean existsByTituloIgnoreCaseAndAutorIgnoreCase(String titulo, String autor);

    Optional<Livro> findByTituloIgnoreCaseAndAutorIgnoreCase(String titulo, String autor);
}