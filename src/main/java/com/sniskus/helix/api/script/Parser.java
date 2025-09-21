package com.sniskus.helix.api.script;

import com.sniskus.helix.api.HelixApi;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * A thread-safe parser for {@link Script}s.
 * <p>
 * The {@code Parser} is responsible for reading {@link Source}s and converting
 * them into executable {@code Script} objects.
 * </p>
 *
 * @see Script
 * @see Source
 */
public interface Parser {

	/**
	 * Parses the given source code into an executable script.
	 *
	 * @param source The source to parse
	 * @return The parsed script
	 * @throws IOException     If an I/O error occurs while reading the source
	 * @throws SyntaxException If the source contains syntax errors
	 */
	@Contract(pure = true)
	@NotNull Script parse(@NotNull Source source) throws IOException, SyntaxException;

	/**
	 * Retrieves the singleton parser instance.
	 *
	 * @return The parser instance
	 */
	@Contract(pure = true)
	static @NotNull Parser get() {
		return HelixApi.getApi().getParser();
	}

}