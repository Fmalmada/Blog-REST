package com.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dto.EntradaPostDTO;
import com.blog.modelo.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class EntradaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;
    

    @Test
    public void cuandoSeObtienenLasEntradasElEstadoEs200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void cuandoSeObtienenLasEntradasLaRespuestaDevuelveUnJSON() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void cuandoSeObtienenLasEntradasSeObtieneLaCantidadDeEntradasAdecuada() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void cuandoSeObtieneTodasLasEntradasEstasTienenLosValoresCorrectos() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].contenido", is("Contenido de entrada 1")))
                .andExpect(jsonPath("$[0].tituloEntrada", is("Titulo de entrada 1")))

                .andExpect(jsonPath("$[1].contenido", is("Contenido de entrada 2")))
                .andExpect(jsonPath("$[1].tituloEntrada", is("Titulo de entrada 2")));

    }

    @Test
    public void cuandoGuardoUnaEntradaDevuelvoIS_CREATED() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void cuandoGuardoUnaEntradaDevuelveUnJson() throws  Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                        .content(mapper.writeValueAsString(unaEntradaDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void cuandoGuardoUnaEntradaNoValidaDevuelve400() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                        .content(mapper.writeValueAsString(unaEntradaDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cuandoGuardoUnaEntradaConCategoriasMeDevulveIS_CREATED() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo")
                                    .categorias(Set.of(Categoria.builder().nombre("Ejemplo").build()))
                                    .build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());                            
    }


    @Test
    @Transactional
    @Rollback
    public void cuandoEliminoUnaEntradaMeDevuelveNO_CONTENT() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test 
    public void cuandoEliminoUnaEntradaNoExistenteMeDevuelve404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",777)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback
    public void cuandoEliminoUnaEntradaDosVecesMeDevuelve404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());     
    }

    @Test
    public void cuandoGetdeUnaEntradaMeDevuelveOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                    
    }

    @Test
    public void cuandoGetDeUnaEntradaNoExistenteDevuelve404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas/{id}", 777)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void cuandoGetDeUnaEntradaMeDevuelveLaCorrecta() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.contenido", is("Contenido de entrada 1")))
                .andExpect(jsonPath("$.tituloEntrada", is("Titulo de entrada 1")));

    }

    @Test
    public void cuandoPutUnaEntradaMeDevuelveOK() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/entradas/{id}", 1)
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void cuandoPutUnaEntradaNoValidaDevuelve400() throws Exception {
         EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/entradas/{id}", 1)
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cuandoPutEntradaNoExistenteDevuelve404() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/entradas/{id}",7777)
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback
    public void cuandoPatchDevuelveOK() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/entradas/{id}", 1)
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void cuandoPatchUnaEntradaNoExistetenteNotFound() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/entradas/{id}", 777)
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    
}
