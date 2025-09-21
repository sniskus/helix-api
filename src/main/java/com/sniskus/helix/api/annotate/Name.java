package com.sniskus.helix.api.annotate;

import com.sniskus.helix.api.Identifier;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Renames the annotated element to the value specified in {@code value()}.
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD, PARAMETER })
public @interface Name {

	/**
	 * The new name for the annotated element.
	 *
	 * @return The new name
	 */
	@NotNull @Identifier String value();

}