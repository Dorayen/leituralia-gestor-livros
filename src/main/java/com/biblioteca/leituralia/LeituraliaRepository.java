package com.biblioteca.leituralia;

import com.biblioteca.leituralia.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeituraliaRepository extends JpaRepository<Livro, Long> {
}
