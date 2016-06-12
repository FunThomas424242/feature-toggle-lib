package com.github.funthomas424242.libs.toggle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBiMap;

public class ModifiableFeatureManager extends AbstractFeatureManager {

	static final Logger LOG = LoggerFactory
			.getLogger(ModifiableFeatureManager.class);

	protected final AbstractFeatureManager featureManager;
	protected final HashBiMap<FeatureToggle, String> mapFeatureClassAufFeatureName;
	protected final FeatureStateRepository modifiableFeatureStateRepository;

	protected ModifiableFeatureManager(
			final AbstractFeatureManager initialFeatureManager) {
		this.featureManager = initialFeatureManager;
		this.mapFeatureClassAufFeatureName = featureManager
				.getMapFeatureClassToName();
		this.modifiableFeatureStateRepository = new FeatureStateRepository(
				mapFeatureClassAufFeatureName);
	}

	protected void resetAllToggleStatesToInitialValue() {
		featureManager.getFeatureStateRepository()
				.copyValuesTo(modifiableFeatureStateRepository, true);
	}

	protected void setActive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		modifiableFeatureStateRepository.setFeatureActive(featureName);
	}

	protected void setDeactive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		modifiableFeatureStateRepository.setFeatureDeactive(featureName);
	}

	@Override
	protected FeatureStateRepository getFeatureStateRepository() {
		return modifiableFeatureStateRepository;
	}

	@Override
	protected HashBiMap<FeatureToggle, String> getMapFeatureClassToName() {
		return mapFeatureClassAufFeatureName;
	}

	@Override
	public boolean isModifiable() {
		return true;
	}

	@Override
	protected void registerClass(final FeatureToggle featureTogglesClass,
			final String featureName) {
		featureManager.registerClass(featureTogglesClass, featureName);
		final boolean isEnabled = featureManager.isActive(featureTogglesClass);
		if (isEnabled) {
			modifiableFeatureStateRepository.setFeatureActive(featureName);
			LOG.debug(" als enabled.");
		} else {
			modifiableFeatureStateRepository.setFeatureDeactive(featureName);
			LOG.debug(" als disabled.");
		}
	}

	@Override
	public boolean isActive(final FeatureToggle featureToggle) {
		final String featureName = mapFeatureClassAufFeatureName
				.get(featureToggle);
		return modifiableFeatureStateRepository.isActive(featureName);
	}

}
