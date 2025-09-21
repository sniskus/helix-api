package com.sniskus.helix.api.annotate.arguments;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a parameter as optional, allowing it to be omitted by the user.
 * <p>
 * If a value is not provided, the specified fallback will be passed instead.
 * This annotation provides different sub-annotations to define fallback
 * values for various data types.
 * </p>
 */
@Target({})
public @interface Optional {

	/**
	 * Declares {@code null} as the fallback value for non-primitive types.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Null { }

	/**
	 * Declares a fallback value for a boolean or Boolean parameter.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Boolean {

		boolean value();

	}

	/**
	 * Declares a fallback value for integer parameters, including
	 * byte, short, int, long, and their respective wrappers.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Integer {

		long value();

	}

	/**
	 * Declares a fallback value for floating-point parameters, including
	 * float, double, and their respective wrappers.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Floating {

		double value();

	}

	/**
	 * Declares a fallback value for a char or Character parameter.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Character {

		char value();

	}

	/**
	 * Declares a fallback value for a String parameter.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface String {

		@NotNull java.lang.String value();

	}

	/**
	 * Declares an empty immutable collection as the fallback value
	 * for a Collection, Map, or array parameter.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Empty { }

	/**
	 * Declares a fallback value for an enum parameter.
	 * The value must match the name of an existing enum constant.
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Enum {

		@NotNull java.lang.String value();

	}

	/**
	 * Declares a fallback value for a {@link java.time.Duration} parameter.
	 * Example: {@code 10s}
	 * <p>
	 * See: <a href="https://sniskus.gitbook.io/helix-wiki/data-types#duration">
	 * Helix Documentation</a>
	 * </p>
	 */
	@Retention(RUNTIME)
	@Target(PARAMETER)
	@interface Duration {

		@NotNull java.lang.String value();

	}

}