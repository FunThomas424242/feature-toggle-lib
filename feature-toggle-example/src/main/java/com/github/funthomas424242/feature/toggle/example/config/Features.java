package com.github.funthomas424242.feature.toggle.example.config;

import com.github.funthomas424242.libs.toggle.annotations.Feature;
import static com.github.funthomas424242.libs.toggle.annotations.Feature.State.ENABLED;

import com.github.funthomas424242.libs.toggle.FeatureToggles;

import static com.github.funthomas424242.libs.toggle.annotations.Feature.State.DISABLED;

public enum Features implements FeatureToggles {

	/**
	 * Aktivierung/Deaktivierung der Toggles über SystemProperties bei
	 * spring-boot: mvn spring-boot:run
	 * -Drun.jvmArguments="-DFEATURE_HALLO=true"
	 */
	@Feature(ENABLED) FEATURE_HELLO, @Feature(DISABLED) FEATURE_HALLO, @Feature(DISABLED) FEATURE_HERO;

	Features() {
		registerClass(this, this.name());
	}
}
