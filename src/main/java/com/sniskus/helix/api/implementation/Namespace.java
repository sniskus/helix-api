package com.sniskus.helix.api.implementation;

import com.sniskus.helix.api.Identifier;
import com.sniskus.helix.api.script.Dictionary;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a namespace which allows registration of methods,
 * fields, types, and triggers under a scoped identifier.
 */
public interface Namespace {

	/**
	 * Creates a new, or returns an already existing, sub-namespace.
	 *
	 * @param key The key of the namespace
	 * @return The namespace with the specified key
	 */
	@NotNull Namespace namespace(@NotNull @Identifier String key);

	/**
	 * Creates a new extension for a type. This method does not
	 * require the type to be previously registered and may
	 * target types that do not yet exist.
	 *
	 * @param type The Java class to extend
	 * @param <T>  The type of the class
	 * @return The {@link Type} representation
	 * @see Type
	 */
	@Contract(value = "_ -> new", pure = true)
	<T> @NotNull Type<T> extend(@NotNull Class<T> type);

	/**
	 * Registers a new type.
	 *
	 * @param type The Java class to register
	 * @param <T>  The type of the class
	 * @return The {@link Type} representation
	 * @see Type
	 */
	@Contract(value = "_ -> new")
	<T> @NotNull Type<T> type(@NotNull Class<T> type);

	/**
	 * Registers a new trigger with a custom handler.
	 *
	 * @param key     The identifier for the trigger
	 * @param type    The type class
	 * @param handler The predicate used to determine whether to invoke the trigger
	 * @param <T>     The type of the passed object
	 * @return This namespace
	 * @see Trigger
	 */
	@Contract(value = "_, _, _ -> this")
	<T> @NotNull Namespace trigger(
			@NotNull @Identifier String key,
			@NotNull Class<T> type,
			@NotNull BiPredicate<@NotNull T, @NotNull Dictionary> handler);

	/**
	 * Registers a custom trigger type.
	 *
	 * @param key     The identifier for the trigger
	 * @param trigger The trigger class
	 * @return This namespace
	 * @see Trigger
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Namespace trigger(
			@NotNull @Identifier String key,
			@NotNull Class<? extends Trigger<?>> trigger);

	/**
	 * Registers a set of static methods.
	 *
	 * @param methods The methods to register
	 * @return This namespace
	 * @see StaticMethods
	 */
	@Contract(value = "_ -> this")
	@NotNull Namespace methods(@NotNull StaticMethods methods);

	/**
	 * Registers a static void method.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This namespace
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Namespace method(
			@NotNull @Identifier String key,
			@NotNull Runnable function);

	/**
	 * Alias for {@link #method(String, Runnable)}.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This namespace
	 */
	@Contract(value = "_, _ -> this")
	default @NotNull Namespace action(
			@NotNull @Identifier String key,
			@NotNull Runnable function) {
		return method(key, function);
	}

	/**
	 * Registers a static non-void method.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This namespace
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Namespace method(
			@NotNull @Identifier String key,
			@NotNull Supplier<? extends @Nullable Object> function);

	/**
	 * Alias for {@link #method(String, Supplier)}.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This namespace
	 */
	@Contract(value = "_, _ -> this")
	default @NotNull Namespace computation(
			@NotNull @Identifier String key,
			@NotNull Supplier<? extends @Nullable Object> function) {
		return method(key, function);
	}

	/**
	 * Registers a static immutable field.
	 *
	 * @param key   The field identifier
	 * @param value The field value
	 * @return This namespace
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Namespace field(
			@NotNull @Identifier String key,
			@Nullable Object value);

	/**
	 * Registers a static immutable field.
	 *
	 * @param key    The field identifier
	 * @param getter The field getter
	 * @return This namespace
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Namespace field(
			@NotNull @Identifier String key,
			@NotNull Supplier<? extends @Nullable Object> getter);

	/**
	 * Registers a static mutable field.
	 *
	 * @param key    The field identifier
	 * @param type   The field type
	 * @param getter The value getter
	 * @param setter The value setter
	 * @param <T>    The type of the field
	 * @return This namespace
	 */
	@Contract(value = "_, _, _, _ -> this")
	<T> @NotNull Namespace field(
			@NotNull @Identifier String key,
			@NotNull Class<T> type,
			@NotNull Supplier<? extends @Nullable T> getter,
			@NotNull Consumer<? extends @Nullable T> setter);

}