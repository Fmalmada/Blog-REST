package com.blog.service;



import org.springframework.stereotype.Service;

import com.blog.dto.ComentarioDTO;
import com.blog.dto.ComentarioPostDTO;

@Service
public interface ComentarioService {
    
    long crearComentario(Long entradaID, ComentarioPostDTO unComentario);

    ComentarioDTO getComentario(long entradaId, long id);

    void eliminarComentario(long entradaId,long id);

    ComentarioPostDTO putComentario(Long entradaId,Long comentarioId,ComentarioPostDTO unComentario);


}