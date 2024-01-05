package com.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.dto.ComentarioDTO;
import com.blog.dto.ComentarioPostDTO;
import com.blog.service.ComentarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/entradas/")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/{entradaId}/comentar")
    public ResponseEntity<ComentarioDTO> crearComentario(@PathVariable long entradaId, @RequestBody @Validated ComentarioPostDTO comentarioPost, UriComponentsBuilder uriBuilder) {
        ComentarioDTO comentario = comentarioService.crearComentario(entradaId, comentarioPost);
		UriComponents uriComponents =  uriBuilder.path(entradaId + "/{id}").buildAndExpand(comentario.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(comentario);
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
   public ResponseEntity<ComentarioDTO> putComentario(@PathVariable long entradaId,@PathVariable long id, @RequestBody @Validated ComentarioPostDTO comentarioPost) {
        return ResponseEntity.ok(comentarioService.putComentario(entradaId,id, comentarioPost));
   }
    
}
