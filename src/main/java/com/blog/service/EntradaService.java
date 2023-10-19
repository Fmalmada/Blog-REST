package com.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blog.dto.EntradaDTO;


public interface EntradaService {
	
	ResponseEntity<EntradaDTO> crearEntrada(EntradaDTO entradaDTO);
	
	EntradaDTO verEntrada(Long id);
	
	List<EntradaDTO> verEntradas();
	
	void eliminarEntrada(Long id);
	
	

}
