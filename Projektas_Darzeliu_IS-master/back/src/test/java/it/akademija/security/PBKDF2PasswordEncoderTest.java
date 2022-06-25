package it.akademija.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

public class PBKDF2PasswordEncoderTest {
	
	private PBKDF2PasswordEncoder encoder = new PBKDF2PasswordEncoder();
	
	@Test
	public void testEncode() {
		assertNotNull(encoder.encode("test"));
		assertNotEquals("test", encoder.encode("test"));
	}
	
	@Test
	public void testMatch() {
		assertTrue(encoder.matches("test", encoder.encode("test")));
		assertFalse(encoder.matches("test", encoder.encode("TEST")));
	}

}
