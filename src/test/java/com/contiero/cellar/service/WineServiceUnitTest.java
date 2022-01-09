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

    @Test
    public void retrieveListOfWinesByRegionTest() {
        final String region = "Italy";
        List<Wine> out = buildListOfWines();
        Mockito.when(repo.findWineByRegion(region)).thenReturn(out);

        List<Wine> got = service.getByRegion(region);
        assertEquals(out, got);
        Mockito.verify(repo, Mockito.times(1)).findWineByRegion(region);
    }

    @Test 
    public void retrieveByTypeAndPriceLessThanTest() {
        final double price = 100.00;
        List<Wine> out = buildListOfWines();
        Mockito.when(repo.findWineByTypeAndPriceLessThan("red", price)).thenReturn(out);

        List<Wine> got = service.getByTypeAndPrice("red", price);
        assertEquals(out, got);
        Mockito.verify(repo, Mockito.times(1)).findWineByTypeAndPriceLessThan("red", price);
    }

    private void checkUpdate(Wine in, Wine out) throws Exception {
        Mockito.when(repo.saveAndFlush(in)).thenReturn(out);
        Mockito.when(repo.findById(6)).thenReturn(Optional.of(in));
        
        service.update(in.getId(), out);
        
        Mockito.verify(repo, Mockito.times(1)).findById(6);
        Mockito.verify(repo, Mockito.times(1)).saveAndFlush(out);
    }

    @Test
    public void updateNumberOfBottlesTest() throws Exception {
        Wine in = new Wine(6, "Bordeaux Superieur", "red", "Belgrave", 78.00,  2001, "France", 12);
        Wine out = new Wine(6, "Bordeaux Superieur", "red", "Belgrave", 78.00,  2001, "France", 24);
        checkUpdate(in, out);
    }

    @Test 
    public void updateProducerTest() throws Exception {
        Wine in = new Wine(6, "Bordeaux Superieur", "red", "Belgrave", 78.00,  2001, "France", 12);
        Wine out = new Wine(6, "Bordeaux Superieur", "red", "Belgravii", 78.00,  2001, "France", 24);
        checkUpdate(in, out);        
    }

    @Test 
    public void updateAll() throws Exception {
        // as we can see above, updates are similar. So here we update all fields at once.
        Wine in = new Wine(6, "Bordeaux Superieur", "rose", "Belgrave", 78.00,  2001, "Franci", 12);
        Wine out = new Wine(6, "Bordeaux", "red", "Bertinerie", 12.00,  2002, "France", 24);
        checkUpdate(in, out);        
    }

}
