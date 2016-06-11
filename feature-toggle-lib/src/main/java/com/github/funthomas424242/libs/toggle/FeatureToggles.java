package com.github.funthomas424242.libs.toggle;

import com.google.common.collect.HashBiMap;

public interface FeatureToggles {

	final HashBiMap<FeatureToggles, String> mapFeatureClassAufFeatureName = HashBiMap
			.create();

	final FeatureStateRepository featureStateRepository = new FeatureStateRepository(
			mapFeatureClassAufFeatureName);

	public default void registerClass(final FeatureToggles featureTogglesClass,
			final String featureName) {
		System.out.println("REGISTER CLASS:" + this.hashCode() + " WITH NAME: "
				+ featureName);
		mapFeatureClassAufFeatureName.forcePut(featureTogglesClass,
				featureName);
		try {
			featureStateRepository.registerFeature(featureName);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public default boolean isActive() {
		final String featureName = mapFeatureClassAufFeatureName.get(this);
		return featureStateRepository.isActive(featureName);
	}

}
