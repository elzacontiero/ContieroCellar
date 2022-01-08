package com.contiero.cellar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.contiero.cellar.domain.Wine;
import com.contiero.cellar.repo.WineRepo;

@SpringBootTest
public class WineServiceUnitTest {

    @Autowired
    private WineService service;
    
    @MockBean
    private WineRepo repo;

    List<Wine> buildListOfWines() 
    {
        String producer = "Masi";
        Wine in1 = new Wine(1L, "Barolo",   "red", producer, 65.01, 1985, "Italy", 12);
        Wine in2 = new Wine(2L, "Amarone",  "red", producer, 65.02, 2007, "Italy", 12);
        
        return List.of(in1, in2);
    }

    @Test
    public void createTest() {
        Wine in = new Wine("Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        Wine out = new Wine(123, "Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);

        Mockito.when(repo.save(in)).thenReturn(out);

        assertEquals(out, service.create(in));

        Mockito.verify(repo, Mockito.times(1)).save(in);
    }

    @Test 
    public void retrieveByIDTest() {
        Wine in = new Wine("Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        Wine out = new Wine(123, "Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
        Mockito.when(repo.save(in)).thenReturn(out);

        Optional<Wine> optOut = Optional.of(out);        
        Mockito.when(repo.findById(123)).thenReturn(optOut);

        Wine got = service.create(in);
        assertEquals(in, got);
        
        got = service.getById(out.getId());
        assertEquals(in, got);
        Mockito.verify(repo, Mockito.times(1)).findById(out.getId());        
    }

    @Test
    public void retrieveListOfWinesFromProducerTest() {
        String producer = "Masi";
        List<Wine> out = buildListOfWines();
        Mockito.when(repo.findWineByProducer(producer)).thenReturn(out);

        List<Wine> got = service.getByProducer(producer);
        assertEquals(out, got);
        Mockito.verify(repo, Mockito.times(1)).findWineByProducer(producer);
    }


    @Test
    public void retrieveListOfWinesByTypeTest() {
        final String type = "red";
        List<Wine> out = buildListOfWines();
        Mockito.when(repo.findWineByType(type)).thenReturn(out);

        List<Wine> got = service.getByType(type);
        assertEquals(out, got);
        Mockito.verify(repo, Mockito.times(1)).findWineByType(type);
    }

    @Test 
    public void retrieveListOfWinesUnderPriceTest() {
        final double price = 65.02;
        List<Wine> out = buildListOfWines();
        Mockito.when(repo.findWineByPriceLessThan(price)).thenReturn(out);

        List<Wine> got = service.getCheaperThan(price);
        assertEquals(out, got);
        Mockito.verify(repo, Mockito.times(1)).findWineByPriceLessThan(price);
    }
}
