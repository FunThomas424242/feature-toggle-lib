package com.github.funthomas424242.libs.toggle;

public interface FeatureToggle {

	public static final FeatureManager featureManager = new FeatureManager();

	public default void registerClass(final FeatureToggle featureTogglesClass,
			final String featureToggleName) {
		featureManager.registerClass(featureTogglesClass, featureToggleName);
	}

	public default boolean isActive() {
		return featureManager.isActive(this);
	}

}
