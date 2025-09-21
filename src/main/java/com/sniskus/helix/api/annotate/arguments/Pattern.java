package com.sniskus.helix.api.annotate.arguments;

import com.sniskus.helix.api.script.ScriptException;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Matcher;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Ensures that a string argument matches a specified regular expression pattern
 * and, if valid, provides a corresponding {@link Matcher} instance.
 * <p>
 * This annotation must be applied to a parameter of type {@link Matcher}.
 * The argument passed is expected to be a string, which is validated
 * against the specified regex pattern. If the argument matches the pattern, a
 * {@link Matcher} instance representing the match will be passed in place of
 * the string. Otherwise, the argument is considered invalid, and a {@link ScriptException}
 * is thrown.
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Pattern {

	/**
	 * The regular expression pattern that the parameter must match.
	 *
	 * @return the regex pattern
	 */
	@NotNull String value();

	/**
	 * Determines whether to validate the string using {@link Matcher#find()}
	 * instead of {@link Matcher#matches()}.
	 *
	 * @return {@code true} if {@link Matcher#find()} should be used;
	 * {@code false} to use {@link Matcher#matches()} instead.
	 */
	boolean find() default false;

}