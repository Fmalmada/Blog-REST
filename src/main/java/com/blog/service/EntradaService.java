package com.blog.service;

import java.util.List;


import com.blog.dto.EntradaDTO;
import com.blog.dto.EntradaPostDTO;


public interface EntradaService {
	
	Long crearEntrada(EntradaPostDTO entradaDTO);
	
	EntradaDTO getEntradas(Long id);
	
	List<EntradaDTO> getEntradas();
	
	void eliminarEntrada(Long id);

	EntradaPostDTO putEntrada(Long id, EntradaPostDTO entradaDTO);

	EntradaPostDTO patchEntrada(Long id, EntradaPostDTO entradDTO);
}
