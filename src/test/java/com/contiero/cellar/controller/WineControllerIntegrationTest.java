package com.contiero.cellar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
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

    @Test
    public void retrieveByIDTest() throws Exception {
        // See wine-data.sql for this entry
        Wine entry = new Wine(2L, "Barolo", "red", "Masi", 65.02, 2007, "Italy", 12);
        String json = mapper.writeValueAsString(entry);

        mvc.perform(get("/wine/readById/2"))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test
    public void retrieveByProducerTest() throws Exception {
        Wine w1 = new Wine(2, "Barolo",  "red", "Masi", 65.02, 2007, "Italy", 12);
        Wine w2 = new Wine(3, "Amarone", "red", "Masi", 65.03, 2003, "Italy", 12);
    
        List<Wine> wines = List.of(w1, w2);
        
        String json = mapper.writeValueAsString(wines);

        mvc.perform(get("/wine/readByProducer/Masi"))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test 
    public void retrieveByTypeTest() throws Exception {
        final String type = "red";
        Wine in1 = new Wine(2L, "Barolo",     type, "Masi",          65.02, 2007, "Italy", 12);
        Wine in2 = new Wine(3L, "Amarone",    type, "Masi",          65.03, 2003, "Italy", 12);
        Wine in3 = new Wine(4L, "Barbaresco", type, "Fontanafredda", 65.04, 2004, "Italy", 12);

        List<Wine> wines = List.of(in1,in2, in3);
        String json = mapper.writeValueAsString(wines);
        mvc.perform(get("/wine/readByType/" + type))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test 
    public void retrieveListOfWinesUnderPriceTest() throws Exception {
        final double price = 65.03;
        Wine in1 = new Wine(5L, "Asti Amabile", "sparkling", "Fontanafredda", 12.00, 2021, "Italy", 12);
        Wine in2 = new Wine(2L, "Barolo",  "red", "Masi", 65.02, 2007, "Italy", 12);
        List<Wine> wines = List.of(in1,in2);
        String json = mapper.writeValueAsString(wines);
        mvc.perform(get("/wine/readCheaperThan/" + price))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

}
