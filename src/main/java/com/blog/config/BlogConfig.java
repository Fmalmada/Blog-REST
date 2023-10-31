package com.blog.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blog.modelo.Entrada;
import com.blog.repository.EntradaRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class BlogConfig {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

    @Bean
    CommandLineRunner inicializarBaseDeDatos(EntradaRepository entradasRepo) {
        return args -> {
            entradasRepo.saveAll(crearEntradas());
        };
    }
    
    List<Entrada> crearEntradas() {
        return Arrays.asList(
            Entrada.builder().contenido("Contenido de entrada 1").tituloEntrada("Titulo de entrada 1").build(),
        Entrada.builder().contenido("Contenido de entrada 2").tituloEntrada("Titulo de entrada 2").build()
        );
    }

}
