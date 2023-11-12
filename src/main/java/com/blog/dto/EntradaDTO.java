package com.blog.dto;



import java.time.LocalDate;
import java.util.Set;

import com.blog.modelo.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EntradaDTO {
	private long id;
	private LocalDate fechaLocal;
	private String tituloEntrada;
	private String contenido;
	private LocalDate fechaActualizacion;
	private Set<Categoria> categorias;
	private Set<ComentarioDTO> comentarios;
}
