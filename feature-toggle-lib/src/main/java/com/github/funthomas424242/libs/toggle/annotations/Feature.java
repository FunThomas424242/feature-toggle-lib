package com.github.funthomas424242.libs.toggle.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Feature {

	public enum State {
		ENABLED, DISABLED
	}

	public State value() default State.DISABLED;

}
