package com.blog.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.blog.repository.ComentarioRepository;
import com.blog.repository.EntradaRepository;
import com.blog.excepciones.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class EntradaServiceTest {
	
	@Mock
	@Autowired
	private EntradaRepository entradasRepo;

	@Mock
	@Autowired
	private CategoriaRepository categoriaRepo;

	@Mock
	@Autowired
	private ComentarioRepository comentariosRepo;


	@Mock
	private EntradaMapper entradaMapper;
	@Mock
	private EntradaPostMapper entradaPostMapper;
	
	
	@InjectMocks
	private EntradaServiceImp entradasService;
	
	private List<Entrada> listaEntradas;
	private List<EntradaDTO> listaEntradasDTO;

	private Entrada entrada;
	private EntradaDTO entradaDTO;
	private EntradaPostDTO entradaPost;
	
	@BeforeEach
	void setUp() {
		entrada = Entrada.builder()
				.tituloEntrada("PrimeraEntrada")
				.contenido("Conetido Generico")
				.id(1)
				.build();
		
		entradaDTO = EntradaDTO.builder()
								.tituloEntrada(entrada.getTituloEntrada())
								.build();
		
		entradaPost = EntradaPostDTO.builder()
											.tituloEntrada(entrada.getTituloEntrada())
											.build();
		
		listaEntradas = Arrays.asList(entrada);
		listaEntradasDTO = Arrays.asList(entradaDTO);
	}
	
	@Test
	public void conseguirTodasEntradas() {
		when(entradasRepo.findAll()).thenReturn(listaEntradas);
		when(entradaMapper.map(listaEntradas)).thenReturn(listaEntradasDTO);

		assertEquals(entradasService.getEntradas(),listaEntradasDTO);

		verify(entradasRepo, times(1)).findAll();
		verify(entradaMapper, times(1)).map(listaEntradas);
		
	}

	@Test
	public void conseguirEntrada() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.of(entrada));
		when(entradaMapper.map(entrada)).thenReturn(entradaDTO);

		assertEquals(entradasService.getEntradas(Long.valueOf(1)), entradaDTO);

		verify(entradasRepo, times(1)).findById(Long.valueOf(1));
		verify(entradaMapper, times(1)).map(entrada);
	}

	@Test
	public void conseguirEntradaNotFound() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> {entradasService.getEntradas(Long.valueOf(1));});
		
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));
	}

	@Test
	public void guardarEntrada() {
		when(entradaPostMapper.map(entradaPost)).thenReturn(entrada);
		when(entradasRepo.save(entrada)).thenReturn(entrada);
		when(entradaMapper.map(entrada)).thenReturn(entradaDTO);

		assertEquals(entradasService.crearEntrada(entradaPost), entradaDTO);

		verify(entradaPostMapper, times(1)).map(entradaPost);
		verify(entradasRepo, times(1)).save(entrada);
		verify(entradaMapper, times(1)).map(entrada);

	}

	@Test
	public void eliminarEntrada() {
		when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);
		doNothing().when(entradasRepo).deleteById(Long.valueOf(1));
		
		entradasService.eliminarEntrada(Long.valueOf(1));

		verify(entradasRepo,times(1)).existsById(Long.valueOf(1));
		verify(entradasRepo, times(1)).deleteById(Long.valueOf(1));
		
	}

	@Test
	public void eliminarEntradaNotFound() {
	when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);

	assertThrows(NotFoundException.class, () -> {entradasService.eliminarEntrada(Long.valueOf(1));});

	verify(entradasRepo, times(1)).existsById(Long.valueOf(1));	
	}

	@Test
	public void putEntrada() {
		when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);
		when(entradaPostMapper.map(entradaPost)).thenReturn(entrada);
		when(entradasRepo.save(entrada)).thenReturn(entrada);
		when(entradaMapper.map(entrada)).thenReturn(entradaDTO);
			
		assertEquals(entradasService.putEntrada(Long.valueOf(1),entradaPost), entradaDTO);

		verify(entradasRepo, times(1)).existsById(Long.valueOf(1));
		verify(entradaPostMapper, times(1)).map(entradaPost);
		verify(entradasRepo, times(1)).save(entrada);
		verify(entradaMapper, times(1)).map(entrada);
	}

	@Test  
	public void putEntradaNotFound() {
		when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);

		assertThrows(NotFoundException.class, () -> {entradasService.putEntrada(Long.valueOf(1),entradaPost);});

		verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

	}

	@Test
	public void patchEntrada() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.of(entrada));
		when(entradasRepo.save(entrada)).thenReturn(entrada);
		when(entradaMapper.map(entrada)).thenReturn(entradaDTO);

		assertEquals(entradasService.patchEntrada(Long.valueOf(1), entradaPost),entradaDTO);
		
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));
		verify(entradasRepo, times(1)).save(entrada);
		verify(entradaMapper, times(1)).map(entrada);

	}

	@Test
	public void patchEntradaNotFound() {
		when(entradasRepo.findById(Long.valueOf(1))).thenReturn(Optional.empty());

		
		assertThrows(NotFoundException.class, () -> {entradasService.patchEntrada(Long.valueOf(1), entradaPost);});
		verify(entradasRepo, times(1)).findById(Long.valueOf(1));

	}
	
}
