package com.stock.database.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableKey {
	public enum Strategy {
		SEQ, NORMAL
	}

	Strategy strategy();

	String sequenceName() default "";
}
