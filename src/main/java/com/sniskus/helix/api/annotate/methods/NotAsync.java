package com.sniskus.helix.api.annotate.methods;

import com.sniskus.helix.api.script.ScriptException;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Prevents a method from being executed asynchronously.
 * <p>
 * If the annotated method is called from an asynchronous context,
 * a {@link ScriptException} will be thrown.
 * </p>
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface NotAsync { }