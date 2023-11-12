package com.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.dto.EntradaDTO;
import com.blog.modelo.Entrada;

@Mapper(componentModel = "spring", uses=ComentarioEntradaMapper.class)
public interface EntradaComentarioMapper{
    
    @Mapping(target="comentarios", ignore=true)
	EntradaDTO map(Entrada entrada);
}
