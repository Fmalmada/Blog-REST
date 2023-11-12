package com.blog.mappers;

import org.mapstruct.Mapper;

import com.blog.dto.EntradaDTO;
import com.blog.modelo.Entrada;

@Mapper(componentModel = "spring", uses=ComentarioEntradaMapper.class)
public interface EntradaMapper{
    
	EntradaDTO map(Entrada entrada);
}
