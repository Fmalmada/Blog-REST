package com.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blog.dto.EntradaDTO;
import com.blog.modelo.Entrada;

public interface EntradaService {
	
	ResponseEntity<EntradaDTO> crearEntrada(EntradaDTO entradaDTO);
	
	Entrada verEntrada(Long id);
	
	List<Entrada> verEntradas();
	
	void eliminarEntrada(Long id);
	
	

}
