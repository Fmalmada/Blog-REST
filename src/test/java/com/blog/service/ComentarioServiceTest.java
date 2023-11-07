package com.blog.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dto.ComentarioDTO;
import com.blog.dto.ComentarioPostDTO;
import com.blog.mappers.ComentarioMapper;
import com.blog.mappers.ComentarioPostMapper;

import com.blog.modelo.Comentario;
import com.blog.modelo.Entrada;
import com.blog.repository.ComentarioRepository;
import com.blog.repository.EntradaRepository;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
	@Autowired
	private EntradaRepository entradasRepo;

	@Mock
	@Autowired
	private ComentarioRepository comentariosRepo;

	@Mock
	private ComentarioMapper comentariosMapper;

	@Mock
    @Autowired
	private ComentarioPostMapper comentarioPostMapper;
	
	
	@InjectMocks
	private ComentarioServiceImp comentarioService;
	
	private Entrada entradaPrueba;
    private ComentarioPostDTO comentarioPost;
    private Comentario comentarioPrueba;
    private ComentarioDTO comentarioDTO;

    @BeforeEach
	void setUp() {
		entradaPrueba = Entrada.builder()
				.tituloEntrada("PrimeraEntrada")
				.contenido("Conetido Generico")
				.id(1)
				.build();
		
		comentarioPrueba = Comentario.builder()
                            .contenido("contenido de Ejemplo")
                            .id(1)
                            .build();
                            
        comentarioPost = ComentarioPostDTO.builder()
                        .contenido("contenido de Ejemplo")
                        .build();
        
        comentarioDTO = ComentarioDTO.builder()
                        .contenido(comentarioPrueba.getContenido())
                        .build();
	}

    @Test
    public void crearComentario() {
        when(entradasRepo.findById(Long.valueOf(1))).
            thenReturn(Optional.of(entradaPrueba));
        
        when(comentariosRepo.save(comentarioPrueba))
            .thenReturn(comentarioPrueba);

        when(comentarioPostMapper.map(comentarioPost))
            .thenReturn(comentarioPrueba);

        assertEquals(comentarioService.crearComentario(entradaPrueba.getId(), comentarioPost), 
                    comentarioPrueba.getId());

        verify(comentariosRepo, times(1)).save(comentarioPrueba);
        verify(comentarioPostMapper, times(1)).map(comentarioPost);
    }

    @Test
    public void getComentario() {
        when(comentariosRepo.findById(Long.valueOf(1)))
            .thenReturn(Optional.of(comentarioPrueba));
        
        when(comentariosMapper.map(comentarioPrueba)).thenReturn(comentarioDTO);

        assertEquals(comentarioService.getComentario(Long.valueOf(1)), comentarioDTO);
        verify(comentariosRepo, times(1)).findById(Long.valueOf(1));
        verify(comentariosMapper, times(1)).map(comentarioPrueba);
    }
	
    
}
