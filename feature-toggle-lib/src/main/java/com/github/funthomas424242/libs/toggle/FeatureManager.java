package com.github.funthomas424242.libs.toggle;

import com.google.common.collect.HashBiMap;

public class FeatureManager extends AbstractFeatureManager {

	protected final HashBiMap<FeatureToggle, String> mapFeatureClassAufFeatureName = HashBiMap
			.create();

	protected final FeatureStateRepository initialFeatureStateRepository = new FeatureStateRepository(
			mapFeatureClassAufFeatureName);

	@Override
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
	}

	@Override
	public boolean isActive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		return initialFeatureStateRepository.isActive(featureName);
	}

	@Override
	protected FeatureStateRepository getFeatureStateRepository() {
		return initialFeatureStateRepository;
	}

	@Override
	protected HashBiMap<FeatureToggle, String> getMapFeatureClassToName() {
		return mapFeatureClassAufFeatureName;
	}

	@Override
	public boolean isModifiable() {
		return false;
	}

}
