package com.github.funthomas424242.libs.toggle;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureProvider {

	static final Logger LOG = LoggerFactory.getLogger(FeatureProvider.class);

	protected Map<Long, AbstractFeatureManager> mapThreadIdToManager = new HashMap<>();

	protected FeatureProvider() {
		final Thread thread = Thread.currentThread();
		final AbstractFeatureManager featureManager = new FeatureManager();
		mapThreadIdToManager.put(thread.getId(), featureManager);
	}

	protected AbstractFeatureManager getFeatureManager() {
		final Thread thread = Thread.currentThread();
		return mapThreadIdToManager.get(thread.getId());
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

	public void registerClass(final FeatureToggle featureTogglesClass,
			final String featureToggleName) {
		LOG.debug(
				"Provider Register ThreadId:" + Thread.currentThread().getId());
		final AbstractFeatureManager featureManager = getFeatureManager();
		featureManager.registerClass(featureTogglesClass, featureToggleName);
	}

	public boolean isActive(final FeatureToggle featureToggle) {
		LOG.debug(
				"Provider isActive ThreadId:" + Thread.currentThread().getId());

		final AbstractFeatureManager featureManager = getFeatureManager();
		LOG.debug("Provider isActive FeatureManager:" + featureManager);
		return featureManager.isActive(featureToggle);
		// if (featureManager == null) {
		// LOG.debug("Provider isActive NULL in ThreadId:"
		// + Thread.currentThread().getId());
		// } else {
		//
		// }
		//
		// return false;
	}

}
