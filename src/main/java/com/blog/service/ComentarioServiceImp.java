package com.blog.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blog.dto.ComentarioPostDTO;
import com.blog.mappers.ComentarioPostMapper;
import com.blog.modelo.Comentario;
import com.blog.modelo.Entrada;
import com.blog.repository.ComentarioRepository;
import com.blog.repository.EntradaRepository;
import com.excepciones.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComentarioServiceImp implements ComentarioService {

    private final ComentarioRepository comentariosRepo;
    private final EntradaRepository entradasRepo;
    private final ComentarioPostMapper comentariosPostMapper;


    public long crearComentario(Long entradaID, ComentarioPostDTO comentarioPostDTO) {
        Entrada entradaAComentar = entradasRepo.findById(entradaID).
                                    orElseThrow(NotFoundException::new);
        
        Comentario comentarioAGuardar = comentariosPostMapper.map(comentarioPostDTO);
        comentarioAGuardar.setUnaEntrada(entradaAComentar);
        Comentario comentarioGuardado = comentariosRepo.save(comentarioAGuardar);
        return comentarioGuardado.getId();
    }

}