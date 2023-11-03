package com.blog.dto;


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
	String tituloEntrada;

	@NotBlank
	@NotNull
	String contenido;
}
