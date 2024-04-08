package com.blog.dto;

import java.time.LocalDateTime;

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
	private LocalDateTime fechaCreacion;	
	private LocalDateTime fechaActualizacion;
    private EntradaDTO entrada;
}