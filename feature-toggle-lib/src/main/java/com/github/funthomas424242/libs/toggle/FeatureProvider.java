package com.github.funthomas424242.libs.toggle;

import java.util.HashMap;
import java.util.Map;

public class FeatureProvider {

	protected Map<Thread, AbstractFeatureManager> mapThreadToManager = new HashMap<>();

	protected FeatureProvider() {
		final Thread thread = Thread.currentThread();
		final AbstractFeatureManager featureManager = new FeatureManager();
		mapThreadToManager.put(thread, featureManager);
	}

	protected AbstractFeatureManager getFeatureManager() {
		final Thread thread = Thread.currentThread();
		return mapThreadToManager.get(thread);
	}

	protected ModifiableFeatureManager getModifiableFeatureManager() {
		final Thread thread = Thread.currentThread();
		final AbstractFeatureManager oldManager = mapThreadToManager
				.get(thread);
		final boolean isModifiable = oldManager.isModifiable();
		if (isModifiable) {
			return (ModifiableFeatureManager) oldManager;
		} else {
			final ModifiableFeatureManager newManager = new ModifiableFeatureManager(
					oldManager);
			mapThreadToManager.put(thread, newManager);
			return newManager;
		}
	}

	public void registerClass(final FeatureToggle featureTogglesClass,
			final String featureToggleName) {
		final AbstractFeatureManager featureManager = getFeatureManager();
		featureManager.registerClass(featureTogglesClass, featureToggleName);
	}

	public boolean isActive(final FeatureToggle featureToggle) {
		final AbstractFeatureManager featureManager = getFeatureManager();
		return featureManager.isActive(featureToggle);
	}

}
