package com.sniskus.helix.api;

import org.intellij.lang.annotations.Language;
import org.intellij.lang.annotations.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Indicates that the annotated {@link String} represents an identifier, such as a
 * variable or method name, and must conform to the regex pattern {@link Identifier#PATTERN}.
 */
@Documented
@Pattern(Identifier.PATTERN)
@Target({ TYPE_USE, METHOD, PARAMETER })
public @interface Identifier {

	/**
	 * The pattern used for validating identifiers.
	 */
	@Language("RegExp")
	String PATTERN = "^[\\p{L}_\\$][\\p{L}0-9_\\$]*$";

}