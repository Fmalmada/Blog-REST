package com.blog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntradaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void cuandoSeObtienenLasEntradasElEstadoEs200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entradas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
}
