package com.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.blog.dto.ComentarioDTO;
import com.blog.dto.ComentarioPostDTO;
import com.blog.service.ComentarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/{entradaId}/comentar")
    public ResponseEntity<ComentarioPostDTO> crearComentario(@PathVariable long entradaId, @RequestBody ComentarioPostDTO comentarioPost, UriComponentsBuilder uriBuilder) {
        Long idComentario = comentarioService.crearComentario(entradaId, comentarioPost);
		UriComponents uriComponents =  uriBuilder.path(entradaId + "/{id}").buildAndExpand(idComentario);
        return ResponseEntity.created(uriComponents.toUri()).body(comentarioPost);
    }

    @GetMapping("/{entradaId}/comentario/{id}")
    public ResponseEntity<ComentarioDTO> getComentario(@PathVariable Long entradaId, @PathVariable long id) {
        return ResponseEntity.ok(comentarioService.getComentario(entradaId, id));
    }

   @DeleteMapping("/{entradaId}/comentario/{id}")
   public ResponseEntity<Long> eliminarComentario(@PathVariable long entradaId,@PathVariable long id) {
        comentarioService.eliminarComentario(entradaId,id);
        return ResponseEntity.noContent().build();
   }

   @PutMapping("/{entradaId}/comentario/{id}")
   public ResponseEntity<ComentarioPostDTO> putComentario(@PathVariable long entradaId,@PathVariable long id, @RequestBody ComentarioPostDTO comentarioPost) {
        return ResponseEntity.ok(comentarioService.putComentario(entradaId,id, comentarioPost));
   }
    
}
