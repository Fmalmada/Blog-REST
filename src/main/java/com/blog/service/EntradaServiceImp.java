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

	public EntradaDTO verEntrada(Long id) {
		Optional<Entrada> entradaOpcional = entradasRepo.findById(id);
		return entradaMapper.EntradatoEntradaDTO(entradaOpcional.orElseThrow());
		
	}

	public List<EntradaDTO> verEntradas() {
		List<Entrada> resultado= entradasRepo.findAll();
		return resultado.stream()
						.map(unaEntrada -> entradaMapper.EntradatoEntradaDTO(unaEntrada))
						.toList();
	}

	public void eliminarEntrada(Long id) {
		entradasRepo.deleteById(id);
	}


}
