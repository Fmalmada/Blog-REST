package com.blog.dto;


import java.util.Set;

import com.blog.modelo.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntradaPostDTO {

	@NotBlank
	@NotNull
	private String tituloEntrada;

	@NotBlank
	@NotNull
	private String contenido;

	private Set<Categoria> categorias;
}
