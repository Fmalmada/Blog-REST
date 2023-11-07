package com.blog.dto;

import java.time.LocalDate;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
   
	private long id;
    private String contenido; 
	private LocalDate fechaLocal;	
	private LocalDate fechaActualizacion;
    private Set<ComentarioDTO> respuestas;
    private ComentarioDTO respuestaA;
    private EntradaDTO unaEntrada;
}