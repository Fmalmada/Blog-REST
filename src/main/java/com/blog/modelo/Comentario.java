package com.blog.modelo;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="entrada_id", nullable=false)
    private Entrada entrada;
}
