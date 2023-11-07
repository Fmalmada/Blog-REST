package com.blog.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.dto.ComentarioDTO;
import com.blog.dto.ComentarioPostDTO;

@Service
public interface ComentarioService {
    
    long crearComentario(Long entradaID, ComentarioPostDTO unComentario);

    ComentarioDTO getComentario(long id);

    List<ComentarioDTO> getComentarios();

    void eliminarComentario(long id);

    ComentarioPostDTO putComentario(Long comentarioId,ComentarioPostDTO unComentario);


}