package com.contiero.cellar.domain;

import org.junit.jupiter.api.Test;

import com.contiero.cellar.domain.Wine;

import nl.jqno.equalsverifier.EqualsVerifier;

public class WineTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Wine.class).usingGetClass().verify();
	}
	
}
