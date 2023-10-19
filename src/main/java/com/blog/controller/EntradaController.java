package com.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.EntradaDTO;
import com.blog.service.EntradaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/entrada")
public class EntradaController {
	
	EntradaService entradaService;

	@PostMapping
	public ResponseEntity<EntradaDTO> crearEntrada(@RequestBody EntradaDTO entradaDTO) {
		return entradaService.crearEntrada(entradaDTO);
	}
	
	@GetMapping
	public List<EntradaDTO> verEntradas() {
		return entradaService.verEntradas();
	}
	
	@GetMapping("/{id}")
	public EntradaDTO verEntrada(@RequestParam long id) {
		return entradaService.verEntrada(id);
		
	}

	@DeleteMapping("/{id}")
	public void eliminarEntrada(@RequestParam long id) {
		entradaService.eliminarEntrada(id);
	} 

}
