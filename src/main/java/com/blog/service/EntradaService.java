package com.blog.service;

import java.util.List;


import com.blog.dto.EntradaDTO;


public interface EntradaService {
	
	Long crearEntrada(EntradaDTO entradaDTO);
	
	EntradaDTO getEntradas(Long id);
	
	List<EntradaDTO> getEntradas();
	
	void eliminarEntrada(Long id);

	EntradaDTO putEntrada(Long id, EntradaDTO entradaDTO);

	EntradaDTO patchEntrada(Long id, EntradaDTO entradDTO);
}
