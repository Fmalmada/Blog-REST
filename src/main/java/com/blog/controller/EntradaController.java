package com.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.blog.dto.EntradaDTO;
import com.blog.dto.EntradaPostDTO;
import com.blog.service.EntradaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/entradas")
public class EntradaController {
	
	private final EntradaService entradaService;

	@PostMapping
	public ResponseEntity<EntradaPostDTO> crearEntrada(@RequestBody EntradaPostDTO entradaDTO, UriComponentsBuilder uriBuilder) {
		Long idEntrada = entradaService.crearEntrada(entradaDTO);
		UriComponents uriComponents =  uriBuilder.path("/entradas/{id}").buildAndExpand(idEntrada);
		return ResponseEntity.created(uriComponents.toUri()).body(entradaDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<EntradaDTO>> verEntradas() {
		return ResponseEntity.ok(entradaService.getEntradas());
	}
	
	@GetMapping("/{id}")
	public EntradaDTO verEntrada(@PathVariable long id) {
		return entradaService.getEntradas(id);
		
	}

	@DeleteMapping("/{id}")
	public  ResponseEntity<Long> eliminarEntrada(@PathVariable long id) {
		entradaService.eliminarEntrada(id);
		return ResponseEntity.noContent().build();
	} 

	@PutMapping("/{id}")
	public ResponseEntity<EntradaPostDTO> putEntrada(@PathVariable long id, @RequestBody EntradaPostDTO entradaDTO) {
		return ResponseEntity.ok().body(entradaService.putEntrada(id, entradaDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EntradaPostDTO> patchEntrada(@PathVariable long id, @RequestBody EntradaPostDTO entradaDTO) {
		return ResponseEntity.ok().body(entradaService.patchEntrada(id, entradaDTO)); 
	}
}
