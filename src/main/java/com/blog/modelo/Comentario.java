package com.blog.modelo;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
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
public class Comentario {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String contenido;

    @CreationTimestamp
	private LocalDate fechaLocal;
	@UpdateTimestamp
	private LocalDate fechaActualizacion;
    
    
    @OneToMany
    @JoinTable(name="respuestas", joinColumns = @JoinColumn(name="comentario_id"),
				inverseJoinColumns= @JoinColumn(name="respuesta_id"))
    private Set<Comentario> respuestas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "respuestaa_id", nullable=true)
    private Comentario respuestaA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="entrada_id", nullable=false)
    private Entrada unaEntrada;
}
