package com.biblioteca.leituralia.mapper;

import com.biblioteca.leituralia.dto.LivroDtoRequest;
import com.biblioteca.leituralia.dto.LivroDtoResponse;
import com.biblioteca.leituralia.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface LivroMapper {//Tem que ser interface
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Livro toEntity(LivroDtoRequest dtoRequest);


    LivroDtoResponse toDtoResponse(Livro entity);
}
