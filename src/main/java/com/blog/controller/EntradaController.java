package com.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.modelo.Entrada;
import com.blog.dto.EntradaDTO;
import com.blog.service.EntradaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entrada")
public class EntradaController {
	
	EntradaService entradaService;

	@PostMapping
	public ResponseEntity<EntradaDTO> crearEntrada(@RequestBody EntradaDTO entradaDTO) {
		return entradaService.crearEntrada(entradaDTO);
	}
	
	@GetMapping
	public List<Entrada> verEntradas() {
		return entradaService.verEntradas();
	}
	
	@GetMapping("/{id}")
	public Entrada verEntrada(@RequestParam long id) {
		return entradaService.verEntrada(id);
		
	}

}
