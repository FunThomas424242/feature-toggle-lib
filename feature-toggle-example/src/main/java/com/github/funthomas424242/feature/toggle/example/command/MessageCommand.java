package com.github.funthomas424242.feature.toggle.example.command;

import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HALLO;
import static com.github.funthomas424242.feature.toggle.example.config.Features.FEATURE_HELLO;

import org.springframework.stereotype.Component;

@Component
public class MessageCommand {

	public String getMessage() {
		final StringBuffer message = new StringBuffer();
		message.append("Aktive Features: ");
		boolean keinFeatureAktiv = true;
		if (FEATURE_HALLO.isActive()) {
			message.append("Hallo");
			keinFeatureAktiv = false;
		}
		if (FEATURE_HELLO.isActive()) {
			message.append("Hello");
			keinFeatureAktiv = false;
		}
		if (keinFeatureAktiv) {
			message.append("Kein Feature aktiv");
		}
		return message.toString();
	}

}
