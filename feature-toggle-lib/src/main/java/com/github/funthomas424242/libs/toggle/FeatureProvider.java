package com.github.funthomas424242.libs.toggle;

public class FeatureProvider {

	protected AbstractFeatureManager featureManager;

	public FeatureProvider() {
		featureManager = new FeatureManager();
	}

	protected AbstractFeatureManager getFeatureManager() {
		return featureManager;
	}

	protected AbstractFeatureManager setFeatureManager(
			final AbstractFeatureManager newFeatureManager) {
		final AbstractFeatureManager oldManager = featureManager;
		featureManager = newFeatureManager;
		return oldManager;
	}

	public void registerClass(final FeatureToggle featureTogglesClass,
			final String featureToggleName) {
		featureManager.registerClass(featureTogglesClass, featureToggleName);

	}

	public boolean isActive(final FeatureToggle featureToggle) {
		return featureManager.isActive(featureToggle);
	}

}
