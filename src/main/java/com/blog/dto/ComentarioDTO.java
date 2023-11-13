package com.blog.dto;

import java.time.LocalDate;


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
    private EntradaDTO entrada;
}