package com.sniskus.helix.api.script;

import com.google.common.base.Preconditions;
import com.sniskus.helix.api.HelixApi;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a source of script with a name and provides
 * a way to access the underlying content as an {@link InputStream}.
 */
public interface Source {

	/**
	 * Returns the name of this source. This can be a file path,
	 * a virtual label (e.g., {@code <string>}), or a unique identifier.
	 *
	 * @return the source name
	 */
	@Contract(pure = true)
	@NotNull String name();

	/**
	 * Returns an input stream to read the content of this source.
	 * <p>
	 * Each call to this method returns a new stream.
	 * </p>
	 *
	 * @return a new {@link InputStream} to read the source
	 * @throws IOException if the source cannot be read
	 */
	@Contract("-> new")
	@NotNull InputStream read() throws IOException;

	interface Writable extends Source {

		/**
		 * Creates a new output stream to which data can be written.
		 * <p>
		 * Each call to this method returns a new stream.
		 * </p>
		 *
		 * @return a new {@link OutputStream} to write to the source
		 * @throws IOException if the source cannot be opened
		 */
		@Contract("-> new")
		@NotNull OutputStream write() throws IOException;

	}

	/**
	 * Creates a {@code Source} from an in-memory string,
	 * using {@code <string>} as the default source name.
	 *
	 * @param code the script content
	 * @return a new in-memory source
	 */
	@Contract(value = "_, -> new", pure = true)
	static @NotNull Source fromString(@NotNull String code) {
		return fromString("<string>", code);
	}

	/**
	 * Creates a {@code Source} from an in-memory string with a custom name.
	 *
	 * @param name the identifier for the source (e.g., for error messages)
	 * @param code the script content
	 * @return a new in-memory source
	 */
	@Contract(value = "_, _, -> new", pure = true)
	static @NotNull Source fromString(@NotNull String name, @NotNull String code) {
		Preconditions.checkNotNull(name);
		Preconditions.checkNotNull(code);
		return new Source() {

			@Override
			public @NotNull String name() {
				return name;
			}

			@Override
			public @NotNull InputStream read() {
				return new ByteArrayInputStream(code.getBytes());
			}
		};
	}

	interface ForFile extends Source.Writable {

		@NotNull Path getPath();

		@Override
		default @NotNull String name() {
			return HelixApi.getApi().getScriptRoot().relativize(this.getPath()).toString();
		}

		@Override
		default @NotNull InputStream read() throws IOException {
			return Files.newInputStream(this.getPath());
		}

		@Override
		default @NotNull OutputStream write() throws IOException {
			return Files.newOutputStream(this.getPath());
		}

	}

	/**
	 * Creates a {@code Source} from a {@link File}.
	 *
	 * @param file the file to read from
	 * @return a new file-based source
	 */
	@Contract(value = "_, -> new", pure = true)
	static Source.@NotNull ForFile fromFile(@NotNull File file) {
		return fromPath(file.toPath());
	}

	/**
	 * Creates a {@code Source} from a {@link Path}.
	 *
	 * @param path the path to read from
	 * @return a new file-based source
	 */
	@Contract(value = "_, -> new", pure = true)
	static Source.@NotNull ForFile fromPath(@NotNull Path path) {
		Preconditions.checkNotNull(path);
		return () -> path;
	}

}