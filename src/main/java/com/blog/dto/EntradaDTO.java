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
	long id;
	LocalDate fechaLocal;
	String tituloEntrada;
	String contenido;
}
