package com.github.funthomas424242.feature.toggle.example.command;

import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HELLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HALLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HERO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageCommandTest {

	MessageCommand command;

	@BeforeClass
	public static void initClass() {
		// System.setProperty("FEATURE_HALLO", "true");
	}

	@Before
	public void setUp() {
		command = new MessageCommand();
	}

	@Test
	public void testGetMessageFeatureUnveraendert() {
		final String message = command.getMessage();
		assertTrue(FEATURE_HELLO.isActive());
		assertFalse(FEATURE_HALLO.isActive());
		assertFalse(FEATURE_HERO.isActive());
		assertEquals("Aktive Features: Hello", message);
	}

}
