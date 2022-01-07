package com.contiero.cellar.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.contiero.cellar.domain.Wine;

import nl.jqno.equalsverifier.EqualsVerifier;

public class WineTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Wine.class).usingGetClass().verify();
	}
	
	@Test
	public void noIdConstructorNotNull() {
		Wine wine = new Wine("Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
		
		// Checks not null
		assertNotNull(wine.getName());
		assertNotNull(wine.getNumberOfBottles());
		assertNotNull(wine.getPrice());
		assertNotNull(wine.getProducer());
		assertNotNull(wine.getRegion());
		assertNotNull(wine.getType());
		assertNotNull(wine.getYear());
	}

	@Test
	public void noIdConstructorValuesOk() {
		Wine wine = new Wine("Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
		
		// Checks values are correct
		assertEquals("Barolo", wine.getName());
		assertEquals(12, wine.getNumberOfBottles());
		assertEquals(65.01 , wine.getPrice());
		assertEquals("Masi" , wine.getProducer());
		assertEquals("Italy" , wine.getRegion());
		assertEquals("red" , wine.getType());
		assertEquals(1985 , wine.getYear());
	}

	@Test
	public void withIdConstructorValuesOk() {
		Wine wine = new Wine(123, "Barolo", "red", "Masi", 65.01, 1985, "Italy", 12);
		
		// Checks values are correct
		assertEquals("Barolo", wine.getName());
		assertEquals(12, wine.getNumberOfBottles());
		assertEquals(65.01 , wine.getPrice());
		assertEquals("Masi" , wine.getProducer());
		assertEquals("Italy" , wine.getRegion());
		assertEquals("red" , wine.getType());
		assertEquals(1985 , wine.getYear());
	}

	@Test 
	public void settersTest() {
		Wine wine = new Wine();

		wine.setName("Barolo");
		wine.setNumberOfBottles(12);
		wine.setPrice(65.01);
		wine.setProducer("Masi");
		wine.setRegion("Italy");
		wine.setType("red");
		wine.setYear(1985);

		// Checks not null
		assertNotNull(wine.getName());
		assertNotNull(wine.getNumberOfBottles());
		assertNotNull(wine.getPrice());
		assertNotNull(wine.getProducer());
		assertNotNull(wine.getRegion());
		assertNotNull(wine.getType());
		assertNotNull(wine.getYear());

		// Checks values are correct
		assertEquals("Barolo", wine.getName());
		assertEquals(12, wine.getNumberOfBottles());
		assertEquals(65.01 , wine.getPrice());
		assertEquals("Masi" , wine.getProducer());
		assertEquals("Italy" , wine.getRegion());
		assertEquals("red" , wine.getType());
		assertEquals(1985 , wine.getYear());
	}
	
 }
