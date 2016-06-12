package com.github.funthomas424242.libs.toggle;

public interface FeatureToggle {

	// TODO jeder Thread braucht seinen eigenen Provider
	public static final FeatureProvider featureProvider = new FeatureProvider();

	public default void registerClass(final FeatureToggle featureTogglesClass,
			final String featureToggleName) {
		featureProvider.registerClass(featureTogglesClass, featureToggleName);
	}

	public default boolean isActive() {
		return featureProvider.isActive(this);
	}

}
