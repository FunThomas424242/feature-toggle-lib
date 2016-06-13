package com.github.funthomas424242.libs.toggle;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureProvider {

	static final Logger LOG = LoggerFactory.getLogger(FeatureProvider.class);

	protected Map<Long, AbstractFeatureManager> mapThreadIdToManager = new HashMap<>();

	protected FeatureProvider() {
		LOG.debug("Provider Constructor ThreadId:"
				+ Thread.currentThread().getId());
	}

	protected AbstractFeatureManager getFeatureManager() {
		final long threadId = Thread.currentThread().getId();
		LOG.debug("Provider getFeatureManager ThreadId:" + threadId);
		return mapThreadIdToManager.get(threadId);
	}

	protected AbstractFeatureManager getFeatureManagerAndIfNullRegisterEnum(
			final FeatureToggle featureToggle) {
		final long threadId = Thread.currentThread().getId();
		LOG.debug("Provider getFeatureManagerAndIfNullRegisterEnum ThreadId:"
				+ threadId);

		if (!mapThreadIdToManager.containsKey(threadId)) {
			final AbstractFeatureManager featureManager = new FeatureManager();
			mapThreadIdToManager.put(threadId, featureManager);
			registerClass(featureToggle, featureToggle.name());
		}
		return mapThreadIdToManager.get(threadId);
	}

	protected ModifiableFeatureManager getModifiableFeatureManager() {
		final Thread thread = Thread.currentThread();
		final AbstractFeatureManager oldManager = mapThreadIdToManager
				.get(thread.getId());
		final boolean isModifiable = oldManager.isModifiable();
		if (isModifiable) {
			return (ModifiableFeatureManager) oldManager;
		} else {
			final ModifiableFeatureManager newManager = new ModifiableFeatureManager(
					oldManager);
			mapThreadIdToManager.put(thread.getId(), newManager);
			return newManager;
		}
	}

	public void registerClass(final FeatureToggle featureToggle,
			final String featureToggleName) {

		final long threadId = Thread.currentThread().getId();
		LOG.debug("Provider Register ThreadId:" + threadId);

		if (!mapThreadIdToManager.containsKey(threadId)) {
			final AbstractFeatureManager featureManager = new FeatureManager();
			mapThreadIdToManager.put(threadId, featureManager);
			registerClass(featureToggle, featureToggle.name());
		} else {
			final AbstractFeatureManager featureManager = mapThreadIdToManager
					.get(threadId);
			featureManager.registerClass(featureToggle, featureToggleName);
		}
	}

	public boolean isActive(final FeatureToggle featureToggle) {
		LOG.debug(
				"Provider isActive ThreadId:" + Thread.currentThread().getId());

		final AbstractFeatureManager featureManager = getFeatureManagerAndIfNullRegisterEnum(
				featureToggle);
		LOG.debug("Provider isActive FeatureManager:" + featureManager);
		return featureManager.isActive(featureToggle);
	}

}
