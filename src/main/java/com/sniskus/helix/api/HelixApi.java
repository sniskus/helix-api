package com.sniskus.helix.api;

import com.sniskus.helix.api.implementation.EventBus;
import com.sniskus.helix.api.implementation.Namespace;
import com.sniskus.helix.api.implementation.Trigger;
import com.sniskus.helix.api.script.Dictionary;
import com.sniskus.helix.api.script.Parser;
import com.sniskus.helix.api.script.Script;
import com.sniskus.helix.api.script.Source;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * The main API class used for interacting with Helix.
 */
public interface HelixApi {

	/**
	 * The singleton instance of the Helix API, if available.
	 */
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	Optional<HelixApi> INSTANCE = ServiceLoader.load(HelixApi.class, HelixApi.class.getClassLoader()).findFirst();

	/**
	 * Returns the API instance, provided that Helix is loaded.
	 *
	 * @return The Helix API instance
	 * @throws NoSuchElementException If Helix isn't present
	 */
	@Contract(pure = true)
	static @NotNull HelixApi getApi() {
		return INSTANCE.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Checks whether an implementation of the Helix API is available.
	 *
	 * @return {@code true} if an implementation is present, {@code false} otherwise
	 */
	@Contract(pure = true)
	static boolean hasImplementation() {
		return INSTANCE.isPresent();
	}

	/**
	 * Returns the path to the root script directory.
	 *
	 * @return The script root path
	 */
	@Contract(pure = true)
	@NotNull Path getScriptRoot();

	/**
	 * Returns the event bus singleton.
	 *
	 * @return The event bus
	 */
	@Contract(pure = true)
	@NotNull EventBus getEventBus();

	/**
	 * Returns the root namespace of a module.
	 *
	 * @param module The module to get the root for
	 * @return The root namespace
	 */
	@Contract(pure = true)
	@NotNull Namespace getRoot(@NotNull HelixModule module);

	/**
	 * Provides access to the script parser, which can be used to parse {@link Script}s.
	 *
	 * @return The singleton parser instance
	 * @see Script#parse(String)
	 * @see Script#parse(Source)
	 */
	@Contract(pure = true)
	@NotNull Parser getParser();

	/**
	 * Creates a new dictionary.
	 *
	 * @return A new dictionary
	 * @see Script#run(Dictionary)
	 * @see Trigger#handle(Object, Dictionary)
	 */
	@Contract(value = "-> new", pure = true)
	@NotNull Dictionary dict();

}