package com.github.funthomas424242.feature.toggle.example.command;

import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HALLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HELLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HERO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MessageCommandTest {

	MessageCommand command;

	@Parameters
	public static Collection<Boolean[]> data() {
		final Boolean[][] data = new Boolean[][]{{false, false, false},
				{false, false, true}, {false, true, false,}};
		return Arrays.asList(data);
	}

	public MessageCommandTest(final boolean featureHelloState,
			final boolean featureHalloState, final boolean featureHeroState) {

		if (featureHelloState) {
			System.setProperty("FEATURE_HELLO", "true");
		} else {
			System.clearProperty("FEATURE_HELLO");
		}
		if (featureHalloState) {
			System.setProperty("FEATURE_HALLO", "true");
		} else {
			System.clearProperty("FEATURE_HALLO");
		}
		if (featureHeroState) {
			System.setProperty("FEATURE_HERO", "true");
		} else {
			System.clearProperty("FEATURE_HERO");
		}
		command = new MessageCommand();
	}

	@BeforeClass
	public static void initClass() {

	}

	@Before
	public void setUp() {

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
