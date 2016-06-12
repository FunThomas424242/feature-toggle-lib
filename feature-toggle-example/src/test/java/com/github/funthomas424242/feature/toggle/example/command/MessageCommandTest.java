package com.github.funthomas424242.feature.toggle.example.command;

import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HALLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HELLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HERO;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.funthomas424242.libs.toggle.FeatureToggle;
import com.github.funthomas424242.libs.toggle.FeatureToggleRule;

@RunWith(Parameterized.class)
public class MessageCommandTest {

	@Rule
	public FeatureToggleRule togglRule = new FeatureToggleRule();

	protected MessageCommand command;

	protected boolean featureHelloState;
	protected boolean featureHalloState;
	protected boolean featureHeroState;
	protected String message;

	@Parameters
	public static Collection<Object[]> data() {
		final Object[][] data = new Object[][]{
				{false, false, false, "Aktive Features: Kein Feature aktiv"},
				{false, false, true, "Aktive Features: HeRo"},
				{false, true, false, "Aktive Features: Hallo"}};
		return Arrays.asList(data);
	}

	public MessageCommandTest(final Object featureHelloState,
			final Object featureHalloState, final Object featureHeroState,
			final String message) {

		this.featureHelloState = (boolean) featureHelloState;
		this.featureHalloState = (boolean) featureHalloState;
		this.featureHeroState = (boolean) featureHeroState;
		this.message = message;
		command = new MessageCommand();
	}

	@BeforeClass
	public static void initClass() {
		final FeatureToggle featureHello = FEATURE_HELLO;
		final FeatureToggle featureHallo = FEATURE_HALLO;
		final FeatureToggle featureHero = FEATURE_HERO;
	}

	@Before
	public void setUp() {
		System.out.println("Before");
		if (featureHelloState) {
			togglRule.enable(FEATURE_HELLO);
		} else {
			togglRule.disable(FEATURE_HELLO);
		}
		if (featureHalloState) {
			togglRule.enable(FEATURE_HALLO);
		} else {
			togglRule.disable(FEATURE_HALLO);
		}
		if (featureHeroState) {
			togglRule.enable(FEATURE_HERO);
		} else {
			togglRule.disable(FEATURE_HERO);
		}
	}

	@After
	public void tearDown() {
		System.out.println("After");
	}

	@Test
	public void testGetMessageFeatureUnveraendert() {
		System.out.println("begin Test");
		final String result = command.getMessage();
		assertEquals(featureHelloState, FEATURE_HELLO.isActive());
		assertEquals(featureHalloState, FEATURE_HALLO.isActive());
		assertEquals(featureHeroState, FEATURE_HERO.isActive());
		assertEquals(message, result);
		System.out.println("ende Test");
	}

}
