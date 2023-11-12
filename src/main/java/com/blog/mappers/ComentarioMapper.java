package com.blog.mappers;

import org.mapstruct.Mapper;

import com.blog.dto.ComentarioDTO;
import com.blog.modelo.Comentario;

@Mapper(componentModel = "spring", uses=EntradaComentarioMapper.class)
public interface ComentarioMapper {
    ComentarioDTO map(Comentario unComentario);
}
