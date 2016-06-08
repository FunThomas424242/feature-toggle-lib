package com.github.funthomas424242.libs.toggle;

import java.util.HashSet;
import java.util.Set;

public class FeatureToggles {

	protected final Set<Class> featureClasses = new HashSet<>();

	public FeatureToggles() {
		// hier alle @Feature suchen und in Set eintragen
		// da über Annotation Scanner Frameworks schwierig
		// (Deklaration classpath, jars, classes notwendig)
		// Dann lieber per SPI die Klassen suchen und die Annotations abfragen
	}

}
