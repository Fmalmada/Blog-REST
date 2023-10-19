package com.blog.service;

import java.util.List;


import com.blog.dto.EntradaDTO;


public interface EntradaService {
	
	EntradaDTO crearEntrada(EntradaDTO entradaDTO);
	
	EntradaDTO getEntradas(Long id);
	
	List<EntradaDTO> getEntradas();
	
	void eliminarEntrada(Long id);
	
}
