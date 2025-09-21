package com.sniskus.helix.api.annotate.arguments;

import com.sniskus.helix.api.script.ScriptException;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Ensures that a parameter is not {@code null}.
 * <p>
 * This annotation is used to enforce non-null constraints on method or constructor parameters.
 * If a parameter annotated with {@code @NotNull} is set to {@code null}, a {@link ScriptException} will be thrown.
 * </p>
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface NotNull { }