package com.github.funthomas424242.libs.toggle.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Feature {

	public enum State {
		ENABLED, DISABLED
	}

	public State state() default State.DISABLED;

}
