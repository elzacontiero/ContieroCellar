package com.contiero.cellar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.contiero.cellar.domain.Wine;
import com.contiero.cellar.service.WineService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest
public class WineControllerUnitTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private WineService service;

    @Test
    public void createTest() throws Exception {
        Wine wine = new Wine("Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        String json = mapper.writeValueAsString(wine);

        Mockito.when(service.create(wine)).thenReturn(wine);

        mvc.perform(post("/wine/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(content().json(json))
            ;
    }
}
