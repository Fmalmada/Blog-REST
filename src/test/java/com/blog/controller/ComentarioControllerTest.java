package com.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import com.blog.dto.ComentarioPostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class ComentarioControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Transactional
    @Test
    public void crearComentario() throws Exception{
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder().contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/1/comentar")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                                .content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Transactional
    @Test
    public void crearComentarioSinAutorizacion() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder()
                                                            .contenido("Contenido de ejemplo")
                                                            .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/1/comentar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isUnauthorized());
    }

    @Transactional
    @Test
    public void crearComentarioAUnaEntradaQueNoExiste() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder()
                                                            .contenido("Contenido de ejemplo")
                                                            .build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/5/comentar")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    public void crearComentarioNoValido() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.post("/1/comentar")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isBadRequest());
    
    }

    @Test
    public void getComentario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1/comentario/1")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido", is("UnComentario"))); 
        }

    @Test
    public void getComentarioDeUnaEntradaQueNoExiste() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/9/comentario/1")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getComentarioQueNoExiste() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1/comentario/5")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    public void eliminarComentario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1/comentario/1")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Transactional
    @Test
    public void eliminarComentarioSinAutorizacion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1/comentario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void eliminarComentarioDeUnaQueNoExiste() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/5/comentario/1")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarComentarioQueNoExiste() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1/comentario/7")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    public void putComentario() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder()
                                                            .contenido("Contenido de ejemplo")
                                                            .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/1/comentario/1")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isOk());
    }

    @Transactional
    @Test
    public void putComentarioSinAutorizacion() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder()
                                                            .contenido("Contenido de ejemplo")
                                                            .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/1/comentario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void putEntradaDeUnaEntradaQueNoExiste() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder()
                                                            .contenido("Contenido de ejemplo")
                                                            .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/7/comentario/1")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putEntradaQueNoExiste() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder()
                                                            .contenido("Contenido de ejemplo")
                                                            .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/1/comentario/7")
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unComentario)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putComentarioNoValido() throws Exception {
        ComentarioPostDTO unComentario = ComentarioPostDTO.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.put("/1/comentario/1")
            .with(httpBasic("user", "password"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(unComentario)))
            .andExpect(status().isBadRequest());
    }
   
}
