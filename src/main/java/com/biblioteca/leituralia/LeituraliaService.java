package com.biblioteca.leituralia;

import com.biblioteca.leituralia.dto.LivroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeituraliaService {
    private final LeituraliaRepository repository;


    private Livro paraEntity(LivroDto livroDto) {
        return Livro.builder()
                .id(livroDto.getId())
                .titulo(livroDto.getTitulo())
                .autor(livroDto.getAutor())
                .categoria(livroDto.getCategoria())
                .anoPublicacao(livroDto.getAnoPublicacao())
                .build();
    }

    private LivroDto paraDto(Livro livro) {
        return LivroDto.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .categoria(livro.getCategoria())
                .anoPublicacao(livro.getAnoPublicacao())
                .build();
    }


    public LivroDto salvar(LivroDto dto) {
        Livro cadastrado = repository.save(paraEntity(dto));
        return paraDto(cadastrado);
    }
}
