package com.blog.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.blog.dto.EntradaDTO;
import com.blog.mappers.EntradaMapper;
import com.blog.modelo.Entrada;
import com.blog.repository.EntradaRepository;
import com.excepciones.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaServiceImp implements EntradaService {
	
	private final EntradaRepository entradasRepo;
	private final EntradaMapper entradaMapper;

	public Long crearEntrada(EntradaDTO entradaDTO) {
		Entrada entradaAGuardar = entradaMapper.EntradaDTOtoEntrada(entradaDTO);
		entradasRepo.save(entradaAGuardar);

		return entradaAGuardar.getId();
	}

	public EntradaDTO getEntradas(Long id) {
		Optional<Entrada> entradaOpcional = entradasRepo.findById(id);
		return entradaMapper.EntradatoEntradaDTO(entradaOpcional.orElseThrow(NotFoundException::new));
		
	}

	public List<EntradaDTO> getEntradas() {
		List<Entrada> resultado= entradasRepo.findAll();
		return resultado.stream()
						.map(unaEntrada -> entradaMapper.EntradatoEntradaDTO(unaEntrada))
						.toList();
	}

	public void eliminarEntrada(Long id) {
		if (!entradasRepo.existsById(id)) {
			throw(new NotFoundException());
		}
		entradasRepo.deleteById(id);
	}

	public EntradaDTO putEntrada(Long id, EntradaDTO entradaDTO) {
		if (!entradasRepo.existsById(id)) {
			throw(new NotFoundException());
		}
		entradasRepo.save(entradaMapper.EntradaDTOtoEntrada(entradaDTO));
		return entradaDTO;
	}

    public EntradaDTO patchEntrada(Long id, EntradaDTO entradaDTO) {
		
		Optional<Entrada> entradaOptional = entradasRepo.findById(id);
		
		if (!entradaOptional.isPresent()) {
			throw(new NotFoundException());
		}

		Entrada entrada = entradaOptional.get();

		if (entradaDTO.getContenido() == null) {
			entrada.setContenido(entradaDTO.getContenido());
		}

		if (entradaDTO.getTituloEntrada() == null) {
			entrada.setTituloEntrada(entradaDTO.getTituloEntrada());
		}

		entradasRepo.save(entrada);
        return entradaDTO;
    }


}
