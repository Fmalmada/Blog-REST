package com.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.dto.EntradaPostDTO;
import com.blog.modelo.Entrada;

@Mapper(componentModel = "spring")
public interface EntradaPostMapper {
    EntradaPostDTO EntradatoEntradaPostDTO(Entrada entrada);
	
	@Mapping(target = "fechaLocal", ignore = true)
	@Mapping(target="id", ignore = true)
	@Mapping(target="fechaActualizacion", ignore = true)
	@Mapping(target="comentarios", ignore = true)
	Entrada EntradaPostDTOtoEntrada(EntradaPostDTO entradaDTO);
}
