package com.biblioteca.leituralia;

import com.biblioteca.leituralia.exception.LivroJaCadastradoException;
import com.biblioteca.leituralia.exception.LivroNaoEcontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Livro buscarLivroPorID(Long id) {
        Optional<Livro> livroOptional = repository.findById(id);
        if (!livroOptional.isEmpty()) {
            return livroOptional.get();
        } else {
            throw new LivroNaoEcontradoException("Usuário não encontrado");
        }
    }
}
