package com.contiero.cellar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
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


}
