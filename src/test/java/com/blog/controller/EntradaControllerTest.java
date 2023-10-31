package com.blog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.blog.dto.EntradaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntradaControllerTest {

    @Autowired
    MockMvc mockMvc;
    
    private String mapToJSON(EntradaDTO unaEntradaDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(unaEntradaDTO);

        }
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
        EntradaDTO unaEntradaDTO = EntradaDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                .content(mapToJSON(unaEntradaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void cuandoGuardoUnaEntradaDevuelveUnJson() throws  Exception {
        EntradaDTO unaEntradaDTO = EntradaDTO.builder().tituloEntrada("Titulo de ejemplo").
                                    contenido("Contenido de ejemplo").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/entradas")
                        .content(mapToJSON(unaEntradaDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }


    @Test
    public void cuandoEliminoUnaEntradaMeDevuelveNO_CONTENT() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entradas/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
}
