package com.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.blog.config.SecurityConfig;
import com.blog.dto.EntradaPostDTO;
import com.blog.modelo.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


@SpringBootTest
@AutoConfigureMockMvc
public class EntradaControllerTest {

    @Autowired
    MockMvc mockMvc;

   /*  @Autowired
    private WebApplicationContext context;
*/
    @Autowired
    ObjectMapper mapper;

    /*@BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }*/

    @Test
    public void getEntradas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas")
            .with(httpBasic("user", "password"))
            .contentType(MediaType.APPLICATION_JSON))
            
        .andExpect(status().isOk())

        .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

        .andExpect(jsonPath("$", hasSize(2)))

        .andExpect(jsonPath("$[0].contenido", is("Contenido de entrada 1")))
        .andExpect(jsonPath("$[0].tituloEntrada", is("Titulo de entrada 1")))

        .andExpect(jsonPath("$[1].contenido", is("Contenido de entrada 2")))
        .andExpect(jsonPath("$[1].tituloEntrada", is("Titulo de entrada 2")));
    }

    @Transactional
    @Test
    public void postEntrada() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
            .with(httpBasic("user", "password"))
            .content(mapper.writeValueAsString(unaEntradaDTO))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
          
    }

    @Test
    public void postEntradaNoValida() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                        .with(httpBasic("user", "password"))
                        .content(mapper.writeValueAsString(unaEntradaDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Transactional
    @Test
    public void postEntradaConCategorias() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo post").
                                    contenido("Contenido de ejemplo post")
                                    .categorias(Set.of(Categoria.builder().nombre("Ejemplo").build()))
                                    .build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                .with(httpBasic("user", "password"))
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());                            
    }

    @Transactional
    @Test
    public void deleteEntrada() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
                .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test 
    public void deleteEntradNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",777)
        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    public void deleteEntradaDosVeces() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
        .with(httpBasic("user", "password"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
        .with(httpBasic("user", "password"))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());     
    }

    @Test
    public void getEntrada() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas/{id}", 1)
        .with(httpBasic("user", "password"))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.contenido", is("Contenido de entrada 1")))
                .andExpect(jsonPath("$.tituloEntrada", is("Titulo de entrada 1")));
                    
    }

    @Test
    public void getEntradaNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas/{id}", 777)
        .with(httpBasic("user", "password"))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    public void putEntrada() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo Put").
                                    contenido("Contenido de ejemplo put").build();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/entradas/{id}", 1)
        .with(httpBasic("user", "password"))
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void putEntradaNoValida() throws Exception {
         EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/entradas/{id}", 1)
        .with(httpBasic("user", "password"))
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void putEntradaNotFound() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.put("/entradas/{id}",7777)
        .with(httpBasic("user", "password"))
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    public void pathEntrada() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/entradas/{id}", 1)
        .with(httpBasic("user", "password"))
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void patchNotFound() throws Exception {
        EntradaPostDTO unaEntradaDTO = EntradaPostDTO.builder().tituloEntrada("Titulo de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/entradas/{id}", 777)
        .with(httpBasic("user", "password"))
                .content(mapper.writeValueAsString(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
  
}
