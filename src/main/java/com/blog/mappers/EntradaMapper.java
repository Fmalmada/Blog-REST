package com.blog.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.dto.EntradaDTO;
import com.blog.modelo.Entrada;

@Mapper(componentModel = "spring")
public interface EntradaMapper {
	
	EntradaDTO EntradatoEntradaDTO(Entrada entrada);
	
	@Mapping(target = "fechaLocal", ignore = true)
	@Mapping(target="id", ignore =true)
	Entrada EntradaDTOtoEntrada(EntradaDTO entradaDTO);

}
