package com.blog.dto;



import java.time.LocalDateTime;
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
	private LocalDateTime fechaLocal;
	private String tituloEntrada;
	private String contenido;
	private LocalDateTime fechaActualizacion;
	private Set<Categoria> categorias;
	private Set<ComentarioDTO> comentarios;
}
