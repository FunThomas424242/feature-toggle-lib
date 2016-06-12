package com.github.funthomas424242.libs.toggle;

import com.google.common.collect.HashBiMap;

public abstract class AbstractFeatureManager {

	abstract protected FeatureStateRepository getFeatureStateRepository();

	abstract protected HashBiMap<FeatureToggle, String> getMapFeatureClassToName();

	abstract protected void registerClass(
			final FeatureToggle featureTogglesClass, final String featureName);

	abstract public boolean isActive(final FeatureToggle featureToggle);

}
