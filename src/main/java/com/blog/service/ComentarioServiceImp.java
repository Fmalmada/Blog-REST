package com.blog.service;



import org.springframework.stereotype.Service;

import com.blog.dto.ComentarioDTO;
import com.blog.dto.ComentarioPostDTO;
import com.blog.mappers.ComentarioMapper;
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
    private final ComentarioMapper comentarioMapper;

    private void existeEntrada(Long entradaId) {
        if (!entradasRepo.existsById(entradaId)) {
            throw(new NotFoundException());
        }     
    }


    public long crearComentario(Long entradaID, ComentarioPostDTO comentarioPostDTO) {
        Entrada entradaAComentar = entradasRepo.findById(entradaID).
                                    orElseThrow(NotFoundException::new);
        
        Comentario comentarioAGuardar = comentariosPostMapper.map(comentarioPostDTO);
        comentarioAGuardar.setEntradaId(entradaAComentar);
        Comentario comentarioGuardado = comentariosRepo.save(comentarioAGuardar);
        return comentarioGuardado.getId();
    }


    public ComentarioDTO getComentario(long entradaId,long id) {
        existeEntrada(entradaId);                   
        return(comentarioMapper.map(comentariosRepo.findById(id).orElseThrow(NotFoundException::new)));
    }

    @Override
    public void eliminarComentario(long entradaId, long id) {
        existeEntrada(entradaId);

        if (comentariosRepo.existsById(id)) {
            comentariosRepo.deleteById(id);
        }
        else {
            throw(new NotFoundException());
        }
    }


    @Override
    public ComentarioPostDTO putComentario(Long entradaId, Long comentarioId, ComentarioPostDTO comentarioDTO) {
        existeEntrada(entradaId);
        Comentario comentarioAEditar = comentariosRepo.findById(comentarioId).orElseThrow(NotFoundException::new);
        comentarioAEditar.setContenido(comentarioDTO.getContenido());

        comentariosRepo.save(comentarioAEditar);
        return comentarioDTO;

    }

}