package com.sniskus.helix.api.script;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;

/**
 * Represents a compiled script which can be executed.
 * <p>
 * A script can be executed with or without a provided {@link Dictionary}
 * containing runtime variables.
 * </p>
 */
public interface Script extends Runnable {

	/**
	 * Executes the script without any provided runtime variables.
	 */
	@Override
	void run();

	/**
	 * Executes the script with the provided dictionary containing runtime variables.
	 *
	 * @param dict The dictionary of variables available during execution
	 */
	void run(@NotNull Dictionary dict);

	/**
	 * Writes this script to an output stream.
	 *
	 * @param stream The stream to write to
	 * @throws IOException If an I/O error occurs
	 */
	void write(@NotNull OutputStream stream) throws IOException;

	/**
	 * Compiles this script into a multi-line string.
	 *
	 * @return The compiled script
	 */
	default @NotNull String compile() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			write(stream);
		} catch (IOException impossible) {
			throw new UncheckedIOException(impossible);
		}
		return stream.toString();
	}

	/**
	 * Parses a script from the given string.
	 *
	 * @param string The string representation of the script
	 * @return The parsed script instance
	 * @throws SyntaxException If the script contains syntax errors
	 */
	@Contract(value = "_, -> new")
	static @NotNull Script parse(@NotNull String string) throws SyntaxException {
		try {
			return parse(Source.fromString(string));
		} catch (IOException impossible) {
			throw new UncheckedIOException(impossible);
		}
	}

	/**
	 * Parses a script from the given source.
	 *
	 * @param source The source containing the script code
	 * @return The parsed script instance
	 * @throws IOException     If an I/O error occurs while reading the source
	 * @throws SyntaxException If the script contains syntax errors
	 */
	@Contract(value = "_, -> new")
	static @NotNull Script parse(@NotNull Source source) throws IOException, SyntaxException {
		return Parser.get().parse(source);
	}

}