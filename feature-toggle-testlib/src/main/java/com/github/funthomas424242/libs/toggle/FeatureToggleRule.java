package com.github.funthomas424242.libs.toggle;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureToggleRule implements TestRule {

	static final Logger LOG = LoggerFactory.getLogger(FeatureToggleRule.class);

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
		LOG.debug("begin apply" + description.getMethodName());
		this.base = base;
		this.description = description;
		lifeFeatureManager.resetAllToggleStatesToInitialValue();
		LOG.debug("reset all toggle states to initial");
		final Statement newStatement = new MyStatement(base);
		LOG.debug("end apply" + description.getMethodName());
		return newStatement;
	}

	public class MyStatement extends Statement {
		private final Statement base;

		public MyStatement(final Statement base) {
			this.base = base;
		}

		@Override
		public void evaluate() throws Throwable {
			LOG.debug("begin evaluate" + description.getMethodName());
			try {

				base.evaluate();
			} finally {
				LOG.debug("end evaluate" + description.getMethodName());
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
