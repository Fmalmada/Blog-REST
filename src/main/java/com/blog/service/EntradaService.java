package com.blog.service;

import java.util.List;


import com.blog.dto.EntradaDTO;
import com.blog.dto.EntradaPostDTO;


public interface EntradaService {
	
	EntradaDTO crearEntrada(EntradaPostDTO entradaDTO);
	
	EntradaDTO getEntradas(Long id);
	
	List<EntradaDTO> getEntradas();
	
	void eliminarEntrada(Long id);

	EntradaDTO putEntrada(Long id, EntradaPostDTO entradaDTO);

	EntradaDTO patchEntrada(Long id, EntradaPostDTO entradDTO);
}
