package com.github.funthomas424242.libs.toggle;

import java.util.HashMap;
import java.util.Map;

import com.github.funthomas424242.libs.toggle.annotations.Feature;
import com.github.funthomas424242.libs.toggle.annotations.Feature.State;
import com.google.common.collect.BiMap;

public class FeatureStateRepository {

	final protected BiMap<FeatureToggles, String> mapFeatureClassAufFeatureName;

	final protected HashMap<String, Boolean> featureStates = new HashMap<>();

	public FeatureStateRepository(
			final BiMap<FeatureToggles, String> toggleMap) {
		this.mapFeatureClassAufFeatureName = toggleMap;
	}

	public boolean isActive(final String featureName) {
		if (featureStates.containsKey(featureName)) {
			return featureStates.get(featureName);
		} else {
			return false;
		}
	}

	public void registerFeature(final String featureName)
			throws NoSuchFieldException, SecurityException {
		final Map<String, FeatureToggles> nameToClass = mapFeatureClassAufFeatureName
				.inverse();
		final FeatureToggles featureToggle = nameToClass.get(featureName);
		final Feature feature = featureToggle.getClass().getField(featureName)
				.getAnnotation(Feature.class);
		final State state = feature.value();
		if (State.ENABLED.equals(state)) {
			featureStates.put(featureName, true);
		}
		if (State.DISABLED.equals(state)) {
			featureStates.put(featureName, false);
		}
		if (System.getProperty(featureName) != null) {
			final boolean status = Boolean.getBoolean(featureName);
			featureStates.put(featureName, status);
		}
	}

}
