package com.sniskus.helix.api.script;

import com.sniskus.helix.api.HelixApi;
import com.sniskus.helix.api.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

/**
 * Represents a dictionary storing key-value pairs (variables).
 * May be accessed after execution to retrieve values of variables.
 */
public interface Dictionary {

	/**
	 * Convenience method for creating a dictionary.
	 * Delegates to {@link HelixApi#dict()}.
	 *
	 * @return A new dictionary
	 */
	@NotNull
	@Contract(value = "-> new", pure = true)
	static Dictionary create() { return HelixApi.getApi().dict(); }

	/**
	 * Retrieves the value associated with the given key.
	 *
	 * @param key The key to look up
	 * @return The associated value, or {@code null} if the key does not exist
	 */
	@Contract(pure = true)
	@Nullable Object get(@NotNull @Identifier String key);

	/**
	 * Inserts or updates a key-value pair in the dictionary.
	 *
	 * @param key   The key to store the value under
	 * @param value The value to store
	 */
	@Contract(mutates = "this")
	void put(@NotNull @Identifier String key, @Nullable Object value);

	/**
	 * Performs the given action for each key-value pair in the dictionary.
	 *
	 * @param action The action to perform on each entry
	 */
	void forEach(@NotNull BiConsumer<@NotNull @Identifier String, @Nullable Object> action);

}