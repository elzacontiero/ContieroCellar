package com.contiero.cellar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.contiero.cellar.domain.Wine;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:wine-schema.sql", "classpath:wine-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class WineControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired 
    private ObjectMapper mapper;

    @Test
    public void createTest() throws Exception {
        Wine entry = new Wine("Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        Wine result = new Wine(3L, "Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);

        String entryJson = mapper.writeValueAsString(entry);
        String resultJson = mapper.writeValueAsString(result);

        mvc.perform(post("/wine/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(entryJson))
            .andExpect(status().isCreated())
            .andExpect(content().json(resultJson));
    }
}
