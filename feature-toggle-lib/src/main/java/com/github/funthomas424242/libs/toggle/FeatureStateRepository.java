package com.github.funthomas424242.libs.toggle;

import java.util.HashMap;
import java.util.Map;

import com.github.funthomas424242.libs.toggle.annotations.Feature;
import com.github.funthomas424242.libs.toggle.annotations.Feature.State;
import com.google.common.collect.BiMap;

public class FeatureStateRepository {

	final protected BiMap<FeatureToggle, String> mapFeatureClassAufFeatureName;

	final protected HashMap<String, Boolean> featureStates = new HashMap<>();

	public FeatureStateRepository(
			final BiMap<FeatureToggle, String> toggleMap) {
		this.mapFeatureClassAufFeatureName = toggleMap;
	}

	public boolean isActive(final String featureName) {
		if (featureStates.containsKey(featureName)) {
			return featureStates.get(featureName);
		} else {
			return false;
		}
	}

	protected void setFeatureActive(final String featureName) {
		featureStates.put(featureName, true);
	}
	protected void setFeatureDeactive(final String featureName) {
		featureStates.put(featureName, false);
	}

	protected void clearFeatureStates() {
		featureStates.clear();
	}

	protected void copyValuesTo(
			final FeatureStateRepository featureStateRepository,
			final boolean clearBefore) {
		if (clearBefore) {
			featureStateRepository.clearFeatureStates();
		}
		for (final String featureName : featureStates.keySet()) {
			final boolean isEnabled = featureStates.get(featureName);
			if (isEnabled) {
				featureStateRepository.setFeatureActive(featureName);
			} else {
				featureStateRepository.setFeatureDeactive(featureName);
			}
		}
	}

	public void registerFeature(final String featureName)
			throws NoSuchFieldException, SecurityException {
		final Map<String, FeatureToggle> nameToClass = mapFeatureClassAufFeatureName
				.inverse();
		final FeatureToggle featureToggle = nameToClass.get(featureName);
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
