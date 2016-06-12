package com.github.funthomas424242.libs.toggle;

public interface ToggleStateManager {

	public default void resetAllToggleStatesToInitialValue() {
		FeatureToggle.featureManager.resetAllToggleStatesToInitialValue();
	}

	public default void setActive(final FeatureToggle featureToggle) {
		FeatureToggle.featureManager.setActive(featureToggle);
	}

	public default void setDeactive(final FeatureToggle featureToggle) {
		FeatureToggle.featureManager.setDeactive(featureToggle);
	}

}
