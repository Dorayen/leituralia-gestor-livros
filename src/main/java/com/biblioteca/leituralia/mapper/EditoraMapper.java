package com.biblioteca.leituralia.mapper;

import com.biblioteca.leituralia.dto.EditoraDtoRequest;
import com.biblioteca.leituralia.dto.EditoraDtoResponse;
import com.biblioteca.leituralia.dto.LivroDtoRequest;
import com.biblioteca.leituralia.dto.LivroDtoResponse;
import com.biblioteca.leituralia.entity.Editora;
import com.biblioteca.leituralia.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface EditoraMapper {
    @Mapping(target = "id", ignore = true)
    Editora toEntity(EditoraDtoRequest dtoRequest);


    EditoraDtoResponse toDtoResponse(Editora entity);
}
