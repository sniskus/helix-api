package com.sniskus.helix.api;

import com.sniskus.helix.api.implementation.Namespace;
import org.jetbrains.annotations.NotNull;

/**
 * Represents and stores data about a Helix module.
 */
public interface HelixModule {

	/**
	 * Returns the root namespace for this module.
	 * Delegates to {@link HelixApi#getRoot(HelixModule)}.
	 *
	 * @return The root namespace
	 */
	default @NotNull Namespace root() {
		return HelixApi.getApi().getRoot(this);
	}

	/**
	 * Returns the id of the addon. The id is used for
	 * identifying the module from imports, commands etc.
	 *
	 * @return The id
	 */
	@Identifier
	@NotNull String getId();

	/**
	 * Returns the name of the module.
	 *
	 * @return The name
	 */
	@NotNull String getName();

	/**
	 * Returns the description of the module. The use of text
	 * blocks or new lines is advised.
	 *
	 * @return The description
	 */
	@NotNull String getDescription();

	/**
	 * Returns the version of the module.
	 *
	 * @return The version
	 */
	@NotNull String getVersion();

	/**
	 * Returns the author of the module (you!).
	 *
	 * @return The author
	 */
	@NotNull String getAuthor();

	/**
	 * Called when the module is loaded by Helix.
	 */
	void load();

}