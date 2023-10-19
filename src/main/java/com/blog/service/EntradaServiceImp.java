package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.dto.EntradaDTO;
import com.blog.mappers.EntradaMapper;
import com.blog.modelo.Entrada;
import com.blog.repository.EntradaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaServiceImp implements EntradaService {
	
	private final EntradaRepository entradasRepo;
	private final EntradaMapper entradaMapper;

	public ResponseEntity<EntradaDTO> crearEntrada(EntradaDTO entradaDTO) {
		Entrada entradaAGuardar = entradaMapper.EntradaDTOtoEntrada(entradaDTO);
		entradasRepo.save(entradaAGuardar);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Localización", "/entrada/" + entradaAGuardar.getId());

		return ResponseEntity.status(201).headers(headers).build();

	}

	public Entrada verEntrada(Long id) {
		Optional<Entrada> entradaOpcional = entradasRepo.findById(id);
		return entradaOpcional.orElseThrow();
		
	}

	public List<Entrada> verEntradas() {
		List<Entrada> resultado= entradasRepo.findAll();
		return resultado;
	}

	public void eliminarEntrada(Long id) {
		entradasRepo.deleteById(id);
	}


}
