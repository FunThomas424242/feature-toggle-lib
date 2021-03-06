package com.github.funthomas424242.libs.toggle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBiMap;

public class FeatureManager extends AbstractFeatureManager {

	final static Logger LOG = LoggerFactory.getLogger(FeatureManager.class);

	protected final HashBiMap<FeatureToggle, String> mapFeatureClassAufFeatureName = HashBiMap
			.create();

	protected final FeatureStateRepository initialFeatureStateRepository = new FeatureStateRepository(
			mapFeatureClassAufFeatureName);

	@Override
	protected void registerClass(final FeatureToggle featureTogglesClass,
			final String featureName) {
		LOG.debug("REGISTER CLASS:" + this.hashCode() + " WITH NAME: "
				+ featureName);
		mapFeatureClassAufFeatureName.forcePut(featureTogglesClass,
				featureName);
		try {
			initialFeatureStateRepository.registerFeature(featureName);
		} catch (NoSuchFieldException | SecurityException e) {
			LOG.error(e.getMessage(), e);
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
