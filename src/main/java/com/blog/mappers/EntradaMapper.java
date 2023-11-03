package com.blog.mappers;


import org.mapstruct.Mapper;

import com.blog.dto.EntradaDTO;
import com.blog.modelo.Entrada;

@Mapper(componentModel = "spring")
public interface EntradaMapper {
	
	EntradaDTO EntradatoEntradaDTO(Entrada entrada);
	
	
	Entrada EntradaDTOtoEntrada(EntradaDTO entradaDTO);

}
