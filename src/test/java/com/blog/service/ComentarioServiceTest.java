package com.blog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.blog.excepciones.NotFoundException;

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

        when(comentarioPostMapper.map(comentarioPost))
            .thenReturn(comentarioPrueba);
        
        when(comentariosRepo.save(comentarioPrueba))
            .thenReturn(comentarioPrueba);

        when(comentariosMapper.map(comentarioPrueba))
            .thenReturn(comentarioDTO);

        assertEquals(comentarioService.crearComentario(entradaPrueba.getId(), comentarioPost), 
                    comentarioDTO);

        verify(comentariosRepo, times(1)).save(comentarioPrueba);
        verify(comentarioPostMapper, times(1)).map(comentarioPost);
        verify(entradasRepo, times(1)).findById(Long.valueOf(1));
        verify(comentariosMapper, times(1)).map(comentarioPrueba);
    }

    @Test
    public void crearComentarioAUnaEntradaQueNoExiste() {
        when(entradasRepo.findById(Long.valueOf(1))).
            thenReturn(Optional.empty());
        
        assertThrows(NotFoundException.class,
            () ->{comentarioService.crearComentario(entradaPrueba.getId(), comentarioPost);});

        verify(entradasRepo, times(1)).findById(Long.valueOf(1));
    }

    @Test
    public void getComentario() {
        when(comentariosRepo.findById(Long.valueOf(1)))
            .thenReturn(Optional.of(comentarioPrueba));
        
        when(comentariosMapper.map(comentarioPrueba)).thenReturn(comentarioDTO);

        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);

        assertEquals(comentarioService.getComentario(Long.valueOf(1),Long.valueOf(1)), comentarioDTO);
        verify(comentariosRepo, times(1)).findById(Long.valueOf(1));
        verify(comentariosMapper, times(1)).map(comentarioPrueba);
        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

    }

    @Test
    public void getComentarioDeUnaEntradaQueNoExiste() {
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);
        
        assertThrows(NotFoundException.class,
            () ->{comentarioService.getComentario(Long.valueOf(1), Long.valueOf(1));});

        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));
    }

    @Test
    public void getComentarioNotFound() {
        when(comentariosRepo.findById(Long.valueOf(1)))
            .thenReturn(Optional.empty());
        
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);


        assertThrows(NotFoundException.class,
                 () ->{comentarioService.getComentario(Long.valueOf(1),Long.valueOf(1));});

        verify(comentariosRepo, times(1)).findById(Long.valueOf(1));
        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

    }

    @Test
    public void deleteComentario() {
        when(comentariosRepo.existsById(Long.valueOf(1))).thenReturn(true);
        doNothing().when(comentariosRepo).deleteById(Long.valueOf(1));
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);


        comentarioService.eliminarComentario(Long.valueOf(1),Long.valueOf(1));

        verify(comentariosRepo,times(1)).existsById(Long.valueOf(1));
        verify(comentariosRepo, times(1)).deleteById(Long.valueOf(1));
        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));
    }

    @Test
    public void deleteComentarioDeUnaEntradaQueNoExiste() {
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);
        
        assertThrows(NotFoundException.class,
            () ->{comentarioService.eliminarComentario(Long.valueOf(1), Long.valueOf(1));});

         verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

    }

    @Test
    public void deleteComentarioNoFound() {
        when(comentariosRepo.existsById(Long.valueOf(1)))
            .thenReturn(false);
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);


        assertThrows(NotFoundException.class,
                 () ->{comentarioService.eliminarComentario(Long.valueOf(1),Long.valueOf(1));});

        verify(comentariosRepo, times(1)).existsById(Long.valueOf(1));
                verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

    }

    @Test
    public void putComentario() {
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);
        when(comentariosRepo.findById(Long.valueOf(1))).thenReturn(Optional.of(comentarioPrueba));
		when(comentariosRepo.save(comentarioPrueba)).thenReturn(comentarioPrueba);
        when(comentariosMapper.map(comentarioPrueba)).thenReturn(comentarioDTO);


		assertEquals(comentarioService.putComentario(Long.valueOf(1), Long.valueOf(1),comentarioPost),
                     comentarioDTO);
		
        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));
        verify(comentariosRepo, times(1)).save(comentarioPrueba);
		verify(comentariosRepo, times(1)).findById(Long.valueOf(1));
        verify(comentariosMapper, times(1)).map(comentarioPrueba);

    }

    @Test
    public void putComentarioDeUnaEntradaQueNoExiste() {
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(false);
        
        assertThrows(NotFoundException.class,
            () ->{comentarioService.putComentario(Long.valueOf(1), Long.valueOf(1), comentarioPost);});

        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));

    }

    @Test
    public void putComentarioNoFound() {
        when(entradasRepo.existsById(Long.valueOf(1))).thenReturn(true);
        when(comentariosRepo.findById(Long.valueOf(1)))
            .thenReturn(Optional.empty());
        
        assertThrows(NotFoundException.class,
                 () ->{comentarioService.putComentario(Long.valueOf(1), Long.valueOf(1), comentarioPost);});

        verify(entradasRepo, times(1)).existsById(Long.valueOf(1));
        verify(comentariosRepo, times(1)).findById(Long.valueOf(1));
        
    }
	 
}
