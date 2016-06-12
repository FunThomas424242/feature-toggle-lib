package com.github.funthomas424242.libs.toggle;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FeatureToggleRule implements TestRule {

	// pro jvm und Thread eine Instanz (gesichert durch FeatureProvider)
	protected ModifiableFeatureManager lifeFeatureManager;

	protected Statement base;
	protected Description description;
	protected Enum<? extends FeatureToggle>[] toggleEnum;

	public FeatureToggleRule(final Enum<? extends FeatureToggle>[] toggleEnum) {
		// enum wird im Konstruktor verlangt um die Auswertung der SysProps im
		// register sicherzustellen bevor irgendein Test läuft

		this.toggleEnum = toggleEnum;
		lifeFeatureManager = FeatureToggle.featureProvider
				.getModifiableFeatureManager();
	}

	@Override
	public Statement apply(final Statement base,
			final Description description) {
		System.out.println("begin apply" + description.getMethodName());
		this.base = base;
		this.description = description;
		lifeFeatureManager.resetAllToggleStatesToInitialValue();
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
		lifeFeatureManager.setActive(featureToggle);
	}

	public void disable(final FeatureToggle featureToggle) {
		lifeFeatureManager.setDeactive(featureToggle);
	}

}
