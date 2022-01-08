package com.contiero.cellar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
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

    @Test 
    public void retrieveByIDTest() throws Exception {
        Wine wine = new Wine(1, "Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        String json = mapper.writeValueAsString(wine);

        Mockito.when(service.getById(1)).thenReturn(wine);

        mvc.perform(get("/wine/readById/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
        ;
    }

    @Test
    public void retrieveListOfWinesFromProducerTest() throws Exception {
        final String producer = "Masi";
        Wine in1 = new Wine(1L, "Barolo",   "red", producer, 65.01, 1985, "Italy", 12);
        Wine in2 = new Wine(2L, "Amarone",  "red", producer, 65.02, 2007, "Italy", 12);
        Wine in3 = new Wine(3L, "Barbaresco", "red", "Fontanafredda", 65.03, 2000, "Italy", 12);

        List<Wine> winesFromProducer = List.of(in1, in2);
        String json = mapper.writeValueAsString(winesFromProducer);

        Mockito.when(service.getByProducer(producer)).thenReturn(winesFromProducer);

        mvc.perform(get("/wine/readByProducer/" + producer))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test 
    public void retrieveListOfWinesByTypeTest() throws Exception {
        final String type = "red";
        Wine in1 = new Wine(1L, "Barolo", type, "Masi", 65.01, 1985, "Italy", 12);
        Wine in2 = new Wine(2L, "Amarone",type, "Masi", 65.02, 2007, "Italy", 12);
        List<Wine> wines = List.of(in1,in2);
        String json = mapper.writeValueAsString(wines);
        Mockito.when(service.getByType(type)).thenReturn(wines);
        mvc.perform(get("/wine/readByType/" + type))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test 
    public void retrieveListOfWinesUnderPriceTest() throws Exception {
        final double price = 65.02;
        Wine in1 = new Wine(1L, "Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        Wine in2 = new Wine(2L, "Amarone","red", "Masi", 65.02, 2007, "Italy", 12);
        
        List<Wine> wines = List.of(in1, in2);
        String json = mapper.writeValueAsString(wines);
        Mockito.when(service.getCheaperThan(price)).thenReturn(wines);
        mvc.perform(get("/wine/readCheaperThan/" + price))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test 
    public void retrieveListOfWinesByRegionTest() throws Exception {
        final String region = "Italy";
        Wine in1 = new Wine(1L, "Barolo", "red", "Masi", 65.01, 1985, region, 12);
        Wine in2 = new Wine(2L, "Amarone","red", "Masi", 65.02, 2007, region, 12);
        List<Wine> wines = List.of(in1,in2);
        String json = mapper.writeValueAsString(wines);
        Mockito.when(service.getByRegion(region)).thenReturn(wines);
        mvc.perform(get("/wine/readByRegion/" + region))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }

    @Test 
    public void retrieveByTypeAndPriceLessThanTest() throws Exception {
        final double price = 100.00;
        List<Wine> wines = List.of(
            new Wine(2, "Barolo",             "red", "Masi",          65.02, 2007, "Italy", 12),
            new Wine(3, "Amarone",            "red", "Masi",          65.03, 2003, "Italy", 12),
            new Wine(4, "Barbaresco",         "red", "Fontanafredda", 65.04, 2004, "Italy", 12),
            new Wine(6, "Bordeaux Superieur", "red", "Belgrave",      78.00, 2001, "France", 12)
        );
        String json = mapper.writeValueAsString(wines);
        Mockito.when(service.getByTypeAndPrice("red", price)).thenReturn(wines);
        mvc.perform(get("/wine/readByTypeAndPriceLessThan/red/" + price))
            .andExpect(status().isOk())
            .andExpect(content().json(json));
    }
}
