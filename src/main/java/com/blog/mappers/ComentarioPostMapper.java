package com.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.dto.ComentarioPostDTO;
import com.blog.modelo.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioPostMapper {

    @Mapping(target="fechaActualizacion", ignore=true)
    @Mapping(target="fechaLocal", ignore=true)
    @Mapping(target="id", ignore=true)
    @Mapping(target="entradaId", ignore=true)
    Comentario map(ComentarioPostDTO comentarioDTO);
    
}
