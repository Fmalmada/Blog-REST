package com.blog.service;

import java.util.List;
import java.util.Optional;


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

	public EntradaDTO crearEntrada(EntradaDTO entradaDTO) {
		Entrada entradaAGuardar = entradaMapper.EntradaDTOtoEntrada(entradaDTO);
		entradasRepo.save(entradaAGuardar);

		return entradaDTO;
	}

	public EntradaDTO getEntradas(Long id) {
		Optional<Entrada> entradaOpcional = entradasRepo.findById(id);
		return entradaMapper.EntradatoEntradaDTO(entradaOpcional.orElseThrow());
		
	}

	public List<EntradaDTO> getEntradas() {
		List<Entrada> resultado= entradasRepo.findAll();
		return resultado.stream()
						.map(unaEntrada -> entradaMapper.EntradatoEntradaDTO(unaEntrada))
						.toList();
	}

	public void eliminarEntrada(Long id) {
		entradasRepo.deleteById(id);
	}


}
