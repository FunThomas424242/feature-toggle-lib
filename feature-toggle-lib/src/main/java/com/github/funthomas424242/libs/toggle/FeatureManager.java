package com.github.funthomas424242.libs.toggle;

import com.google.common.collect.HashBiMap;

public class FeatureManager {

	protected final HashBiMap<FeatureToggle, String> mapFeatureClassAufFeatureName = HashBiMap
			.create();

	protected final FeatureStateRepository initialFeatureStateRepository = new FeatureStateRepository(
			mapFeatureClassAufFeatureName);

	protected final FeatureStateRepository lifeFeatureStateRepository = new FeatureStateRepository(
			mapFeatureClassAufFeatureName);

	protected void registerClass(final FeatureToggle featureTogglesClass,
			final String featureName) {
		System.out.print("REGISTER CLASS:" + this.hashCode() + " WITH NAME: "
				+ featureName);
		mapFeatureClassAufFeatureName.forcePut(featureTogglesClass,
				featureName);
		try {
			initialFeatureStateRepository.registerFeature(featureName);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final boolean isEnabled = initialFeatureStateRepository
				.isActive(featureName);
		if (isEnabled) {
			lifeFeatureStateRepository.setFeatureActive(featureName);
			System.out.println(" als enabled.");
		} else {
			lifeFeatureStateRepository.setFeatureDeactive(featureName);
			System.out.println(" als disabled.");
		}
	}

	protected void resetAllToggleStatesToInitialValue() {
		initialFeatureStateRepository.copyValuesTo(lifeFeatureStateRepository,
				true);
	}

	protected void setActive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		lifeFeatureStateRepository.setFeatureActive(featureName);
	}

	protected void setDeactive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		lifeFeatureStateRepository.setFeatureDeactive(featureName);
	}

	public boolean isActive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		return lifeFeatureStateRepository.isActive(featureName);
	}
}
