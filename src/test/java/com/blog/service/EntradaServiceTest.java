package com.blog.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import com.blog.dto.EntradaDTO;
import com.blog.dto.EntradaPostDTO;
import com.blog.mappers.EntradaMapper;
import com.blog.mappers.EntradaPostMapper;
import com.blog.modelo.Entrada;
import com.blog.repository.CategoriaRepository;
import com.blog.repository.EntradaRepository;
import com.excepciones.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class EntradaServiceTest {
	
	@Mock
	@Autowired
	private EntradaRepository entradasRepo;

	@Mock
	@Autowired
	private CategoriaRepository categoriaRepo;

	@Mock
	private EntradaMapper entradaMapper;
	@Mock
	private EntradaPostMapper entradaPostMapper;
	
	
	@InjectMocks
	private EntradaServiceImp entradasService;
	
	private List<Entrada> listaEntradas;
	private List<EntradaDTO> listaEntradasDTO;

	private List<EntradaPostDTO> listaEntradasPostDTO;
	
	@BeforeEach
	void setUp() {
		Entrada unaEntrada = Entrada.builder()
				.tituloEntrada("PrimeraEntrada")
				.contenido("Conetido Generico")
				.id(1)
				.build();
		
		EntradaDTO unaEntradaDTo = EntradaDTO.builder()
									.tituloEntrada(unaEntrada.getTituloEntrada())
									.build();
		
		EntradaPostDTO unaEntradaPostDTO = EntradaPostDTO.builder()
											.tituloEntrada(unaEntrada.getTituloEntrada())
											.build();
		
		listaEntradas = Arrays.asList(unaEntrada);
		listaEntradasDTO = Arrays.asList(unaEntradaDTo);
		listaEntradasPostDTO = Arrays.asList(unaEntradaPostDTO);
	}
	
	@Test
	public void conseguirTodasEntradas() {
		when(entradasRepo.findAll()).thenReturn(listaEntradas);
		when(entradaMapper.EntradatoEntradaDTO(any(Entrada.class))).thenReturn(listaEntradasDTO.get(0));
		assertEquals(entradasService.getEntradas(),listaEntradasDTO);

		verify(entradasRepo, times(1)).findAll();
		
	}

	@Test
	public void conseguirEntrada() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.of(listaEntradas.get(0)));
		when(entradaMapper.EntradatoEntradaDTO(any(Entrada.class))).thenReturn(listaEntradasDTO.get(0));
		assertEquals(entradasService.getEntradas(Long.valueOf(1)), listaEntradasDTO.get(0));
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));
	}

	@Test
	public void conseguirEntradaNotFound() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> {entradasService.getEntradas(Long.valueOf(1));});
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));

	}

	@Test
	public void guardarEntrada() {
		when(entradasRepo.save(any(Entrada.class))).thenReturn(listaEntradas.get(0));
		when(entradaPostMapper.EntradaPostDTOtoEntrada(any(EntradaPostDTO.class))).thenReturn(listaEntradas.get(0));
		assertEquals(entradasService.crearEntrada(listaEntradasPostDTO.get(0)), listaEntradas.get(0).getId());
		verify(entradasRepo, times(1)).save(any(Entrada.class));
		verify(entradaPostMapper, times(1)).EntradaPostDTOtoEntrada(any(EntradaPostDTO.class));

	}

	@Test
	public void eliminarEntrada() {
		doNothing().when(entradasRepo).deleteById(any(Long.class));
		when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);

		entradasService.eliminarEntrada(Long.valueOf(1));

		verify(entradasRepo, times(1)).deleteById(any(Long.class));
		
	}

	@Test
	public void eliminarEntradaNotFound() {
	when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);
	assertThrows(NotFoundException.class, () -> {entradasService.eliminarEntrada(Long.valueOf(1));});
	verify(entradasRepo, times(1)).existsById(any(Long.class));	
	}

	@Test
	public void putEntrada() {
		when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);
		when(entradasRepo.save(any(Entrada.class))).thenReturn(listaEntradas.get(0));
		when(entradaPostMapper.EntradaPostDTOtoEntrada(any(EntradaPostDTO.class))).thenReturn(listaEntradas.get(0));

		assertEquals(entradasService.putEntrada(Long.valueOf(1),listaEntradasPostDTO.get(0)), listaEntradasPostDTO.get(0));
		verify(entradasRepo, times(1)).save(any(Entrada.class));
		verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

	}

	@Test  
	public void PutEntradaNotFound() {
		when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {entradasService.putEntrada(Long.valueOf(1),listaEntradasPostDTO.get(0));});

		verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

	}

	@Test
	public void patchEntrada() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.of(listaEntradas.get(0)));
		when(entradasRepo.save(any(Entrada.class))).thenReturn(listaEntradas.get(0));

		assertEquals(entradasService.patchEntrada(Long.valueOf(1), listaEntradasPostDTO.get(0)),listaEntradasPostDTO.get(0));
		verify(entradasRepo, times(1)).save(any(Entrada.class));
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));
	}

	@Test
	public void patchEntradaNotFound() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> {entradasService.patchEntrada(Long.valueOf(1), listaEntradasPostDTO.get(0));});
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));

	}
	
}
