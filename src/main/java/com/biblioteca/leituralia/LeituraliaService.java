package com.biblioteca.leituralia;

import com.biblioteca.leituralia.exception.LivroJaCadastradoException;
import com.biblioteca.leituralia.exception.LivroNaoEcontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Livro atualizarLivro(Long id, Livro livro) {
        Livro existente = buscarLivroPorID(id);

        existente.setTitulo(livro.getTitulo());
        existente.setAutor(livro.getAutor());
        existente.setCategoria(livro.getCategoria());
        existente.setAnoPublicacao(livro.getAnoPublicacao());

        return repository.save(existente);
    }

    public void deletarLivro(Long id) {
        repository.deleteById(id);
    }
}
