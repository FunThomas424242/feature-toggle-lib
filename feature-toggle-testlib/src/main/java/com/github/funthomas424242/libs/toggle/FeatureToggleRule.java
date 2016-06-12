package com.github.funthomas424242.libs.toggle;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FeatureToggleRule implements TestRule, ToggleStateManager {

	protected Statement base;
	protected Description description;

	@Override
	public Statement apply(final Statement base,
			final Description description) {
		System.out.println("begin apply" + description.getMethodName());
		this.base = base;
		this.description = description;
		resetAllToggleStatesToInitialValue();
		System.out.println("reset all toggle states to initial");
		final Statement newStatement = new MyStatement(base);
		System.out.println("end apply" + description.getMethodName());
		return newStatement;
	}

	public class MyStatement extends Statement {
		private final Statement base;

		public MyStatement(final Statement base) {
			this.base = base;
		}

		@Override
		public void evaluate() throws Throwable {
			System.out.println("begin evaluate" + description.getMethodName());
			try {

				base.evaluate();
			} finally {
				System.out
						.println("end evaluate" + description.getMethodName());
			}
		}
	}

	public void enable(final FeatureToggle featureToggle) {
		setActive(featureToggle);
	}

	public void disable(final FeatureToggle featureToggle) {
		setDeactive(featureToggle);
	}

}
