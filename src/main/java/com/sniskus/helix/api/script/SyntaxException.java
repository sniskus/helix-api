package com.sniskus.helix.api.script;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Exception thrown when an error occurs during script parsing.
 * <p>
 * This exception provides details about the specific line where the parsing
 * error occurred, including the line content and its index.
 * </p>
 */
public final class SyntaxException extends Exception {
	private final String line;
	private final int lineIndex;

	/**
	 * Constructs a new {@code ParseException} with the specified reason, line content, and line index.
	 *
	 * @param reason    The reason for the parsing error.
	 * @param line      The content of the line where the error occurred. Must not be {@code null}.
	 * @param lineIndex The 0-based index of the line where the error occurred. Must be non-negative.
	 * @throws NullPointerException     If {@code line} is {@code null}.
	 * @throws IllegalArgumentException If {@code lineIndex} is negative.
	 */
	public SyntaxException(@Nullable String reason, @NotNull String line, int lineIndex) {
		super(reason == null ? "Unknown parse error" : reason);
		this.line = Preconditions.checkNotNull(line);
		Preconditions.checkArgument(lineIndex >= 0, "Line index must be non-negative");
		this.lineIndex = lineIndex;
	}

	/**
	 * Returns the detail message string of this exception.
	 *
	 * @return The detail message string of this {@code SyntaxException} instance.
	 */
	@Override
	public @NotNull String getMessage() { return super.getMessage(); }

	/**
	 * Returns the content of the line where the parsing error occurred.
	 *
	 * @return The line content.
	 */
	public @NotNull String getLine() { return line; }

	/**
	 * Returns the 1-based line number where the parsing error occurred.
	 * <p>
	 * Since the internal index is 0-based, this method returns {@code lineIndex + 1}
	 * to provide a more human-readable line number.
	 * </p>
	 *
	 * @return The 1-based line number.
	 */
	public int getLineNr() { return lineIndex + 1; }

	/**
	 * Returns the 0-based index of the line where the parsing error occurred.
	 *
	 * @return The 0-based line index.
	 */
	public int getLineIndex() { return lineIndex; }

}
