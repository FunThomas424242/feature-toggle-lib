package com.github.funthomas424242.feature.toggle.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger LOG = LoggerFactory
			.getLogger(Application.class);

	public static void main(final String[] args) {
		final Application app = new Application();
		app.start(args);
	}

	private void start(final String[] args) {
		final SpringApplication app = new SpringApplication(Application.class);
		app.setShowBanner(true);
		app.run(args);
	}

}