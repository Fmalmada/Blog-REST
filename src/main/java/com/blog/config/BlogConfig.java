package com.blog.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blog.modelo.Comentario;
import com.blog.modelo.Entrada;
import com.blog.repository.ComentarioRepository;
import com.blog.repository.EntradaRepository;


@Configuration
public class BlogConfig {
	
    @Bean
    CommandLineRunner inicializarBaseDeDatos(EntradaRepository entradasRepo, ComentarioRepository comentariosRepo) {
        return args -> {
            List<Entrada> listaEntradas = crearEntradas();

            Comentario unComentario = Comentario.builder().contenido("UnComentario").build();

           
            unComentario.setEntradaId(listaEntradas.get(0));
            entradasRepo.saveAll(listaEntradas);
            unComentario.setEntradaId(listaEntradas.get(0));
            comentariosRepo.save(unComentario);
  

        };
    }
    
    List<Entrada> crearEntradas() {

        
        
        return Arrays.asList(
            Entrada.builder().contenido("Contenido de entrada 1").tituloEntrada("Titulo de entrada 1").build(),
        Entrada.builder().contenido("Contenido de entrada 2").tituloEntrada("Titulo de entrada 2").build()
        );
    }

}
