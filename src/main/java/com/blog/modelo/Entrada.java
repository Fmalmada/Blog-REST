package com.blog.modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entrada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String tituloEntrada;
	private String contenido;
	@CreationTimestamp
	private LocalDate fechaLocal;
	@UpdateTimestamp
	private LocalDate fechaActualizacion;

	@Builder.Default
	@ManyToMany
	@JoinTable(name="entrada_categoria", joinColumns = @JoinColumn(name="entrada_id"),
				inverseJoinColumns= @JoinColumn(name="categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();

	@Builder.Default
	@OneToMany(mappedBy = "entradaId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comentario> comentarios = new HashSet<>();
}
