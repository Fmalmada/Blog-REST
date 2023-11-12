package com.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.blog.dto.EntradaDTO;
import com.blog.dto.EntradaPostDTO;
import com.blog.mappers.EntradaMapper;
import com.blog.mappers.EntradaPostMapper;
import com.blog.modelo.Categoria;
import com.blog.modelo.Entrada;
import com.blog.repository.CategoriaRepository;
import com.blog.repository.EntradaRepository;
import com.excepciones.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaServiceImp implements EntradaService {
	
	private final EntradaRepository entradasRepo;
	private final EntradaMapper entradaMapper;
	private final EntradaPostMapper entradaPostMapper;
	private final CategoriaRepository categoriasRepo;

	public Long crearEntrada(EntradaPostDTO entradaDTO) {
		Entrada entradaAGuardar = entradaPostMapper.EntradaPostDTOtoEntrada(entradaDTO);

		Set<Categoria> categorias = entradaAGuardar.getCategorias();
		if (categorias.size() != 0) {
			categoriasRepo.saveAll(categorias);
				}

		entradasRepo.save(entradaAGuardar);

		return entradaAGuardar.getId();
	}

	public EntradaDTO getEntradas(Long id) {
		Optional<Entrada> entradaOpcional = entradasRepo.findById(id);
		return entradaMapper.map(entradaOpcional.orElseThrow(NotFoundException::new));
		
	}

	public List<EntradaDTO> getEntradas() {
		List<Entrada> resultado= entradasRepo.findAll();
		return resultado.stream()
						.map(unaEntrada -> entradaMapper.map(unaEntrada))
						.toList();
	}

	public void eliminarEntrada(Long id) {
		if (!entradasRepo.existsById(id)) {
			throw(new NotFoundException());
		}
		entradasRepo.deleteById(id);
	}

	public EntradaPostDTO putEntrada(Long id, EntradaPostDTO entradaDTO) {
		if (!entradasRepo.existsById(id)) {
			throw(new NotFoundException());
		}
		entradasRepo.save(entradaPostMapper.EntradaPostDTOtoEntrada(entradaDTO));
		return entradaDTO;
	}

    public EntradaPostDTO patchEntrada(Long id, EntradaPostDTO entradaDTO) {
		
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
