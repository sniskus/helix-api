package com.sniskus.helix.api.script;

import javax.annotation.Nullable;

/**
 * Script exceptions are used to signal an error during script execution that is
 * caused by incorrect usage or other issues within the user's control. Script exceptions
 * are handled differently by Helix and do not trigger the same type of error logs. These
 * exceptions are expected to provide clear feedback to the user as to what went wrong,
 * although this is not an absolute requirement.
 */
public class ScriptException extends RuntimeException {

	/**
	 * Constructs a new script exception with no detail message.
	 */
	public ScriptException() { }

	/**
	 * Constructs a new script exception with the specified detail message.
	 *
	 * @param message The detail message describing the error
	 */
	public ScriptException(@Nullable String message) { super(message); }

	/**
	 * Constructs a new script exception with the specified detail message and cause.
	 *
	 * @param message The detail message describing the error
	 * @param cause   The underlying cause of the exception
	 */
	public ScriptException(@Nullable String message, @Nullable Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new script exception with the specified cause.
	 *
	 * @param cause The underlying cause of the exception
	 */
	public ScriptException(@Nullable Throwable cause) {
		super(cause);
	}

}