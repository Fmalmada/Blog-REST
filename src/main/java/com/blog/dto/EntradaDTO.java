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
public class EntradaDTO {
	private long id;
	private LocalDate fechaLocal;
	private String tituloEntrada;
	private String contenido;
	private LocalDate fechaActualizacion;
}
