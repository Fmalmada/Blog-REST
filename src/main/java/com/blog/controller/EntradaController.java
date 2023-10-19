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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.blog.dto.EntradaDTO;
import com.blog.service.EntradaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/entradas")
public class EntradaController {
	
	private final EntradaService entradaService;

	@PostMapping
	public ResponseEntity<EntradaDTO> crearEntrada(@RequestBody EntradaDTO entradaDTO, UriComponentsBuilder uriBuilder) {
		UriComponents uriComponents =  uriBuilder.path("/entradas/{id}").buildAndExpand("id");
		ResponseEntity<EntradaDTO> respuesta = ResponseEntity.created(uriComponents.toUri()).body(entradaService.crearEntrada(entradaDTO));
		return respuesta;
	}
	
	@GetMapping
	public ResponseEntity<List<EntradaDTO>> verEntradas() {
		return ResponseEntity.ok(entradaService.getEntradas());
	}
	
	@GetMapping("/{id}")
	public EntradaDTO verEntrada(@RequestParam long id) {
		return entradaService.getEntradas(id);
		
	}

	@DeleteMapping("/{id}")
	public void eliminarEntrada(@RequestParam long id) {
		entradaService.eliminarEntrada(id);
	} 

}
