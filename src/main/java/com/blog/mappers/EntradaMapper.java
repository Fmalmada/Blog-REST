package com.blog.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.blog.dto.EntradaDTO;
import com.blog.modelo.Entrada;

@Mapper(componentModel = "spring", uses=ComentarioEntradaMapper.class)
public interface EntradaMapper{
    
	EntradaDTO map(Entrada entrada);

	List<EntradaDTO> map(List<Entrada> entradas);
}
