package com.blog.mappers;

import com.blog.dto.ComentarioDTO;
import com.blog.modelo.Comentario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioEntradaMapper {
    
    @Mapping(target="entradaId", ignore=true)
    ComentarioDTO map(Comentario comentario);
}
