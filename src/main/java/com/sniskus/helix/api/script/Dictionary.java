package com.sniskus.helix.api.script;

import com.sniskus.helix.api.HelixApi;
import com.sniskus.helix.api.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents a dictionary storing key-value pairs (variables).
 * May be accessed after execution to retrieve values of variables.
 */
public interface Dictionary extends Map<@NotNull @Identifier String, @Nullable Object> {

	/**
	 * Convenience method for creating a dictionary.
	 * Delegates to {@link HelixApi#dict()}.
	 *
	 * @return A new dictionary
	 */
	@NotNull
	@Contract(value = "-> new", pure = true)
	static Dictionary create() { return HelixApi.getApi().dict(); }

}