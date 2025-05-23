package com.biblioteca.leituralia;

import com.biblioteca.leituralia.exception.LivroJaCadastradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeituraliaService {
    private final LeituraliaRepository repository;


    public Livro salvar(Livro livro) {
        if (livro.getId() != null && repository.existsById(livro.getId())) {
            throw new LivroJaCadastradoException("Livro já foi cadastrado");
        }

        return repository.save(livro);
    }
}
