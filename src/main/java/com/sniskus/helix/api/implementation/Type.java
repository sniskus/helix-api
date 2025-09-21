package com.sniskus.helix.api.implementation;

import com.sniskus.helix.api.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a data type, which may be a wrapper of Java class
 * or an extension of an existing type.
 * <p>
 * This interface allows registration of instance methods and fields
 * that operate on objects of type {@code T}.
 * </p>
 *
 * @param <T> the underlying Java type
 * @see Namespace#type(Class)
 * @see Namespace#extend(Class)
 */
public interface Type<T> {

	/**
	 * Registers a set of non-static methods.
	 *
	 * @param methods The class implementing {@link InstanceMethods} for type {@code T}
	 * @return This type
	 * @see InstanceMethods
	 */
	@Contract(value = "_ -> this")
	@NotNull Type<T> methods(@NotNull Class<? extends InstanceMethods<? super T>> methods);

	/**
	 * Registers a non-static void method.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> method(
			@NotNull @Identifier String key,
			@NotNull Consumer<? extends @NotNull T> function);

	/**
	 * Alias for {@link #method(String, Consumer)}.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	default @NotNull Type<T> action(
			@NotNull @Identifier String key,
			@NotNull Consumer<? extends @NotNull T> function) {
		return method(key, function);
	}

	/**
	 * Registers a non-static non-void method.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> method(
			@NotNull @Identifier String key,
			@NotNull Function<? extends @NotNull T, ? extends @Nullable Object> function);

	/**
	 * Alias for {@link #method(String, Function)}.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	default @NotNull Type<T> computation(
			@NotNull @Identifier String key,
			@NotNull Function<? extends @NotNull T, ? extends @Nullable Object> function) {
		return method(key, function);
	}

	/**
	 * Registers a non-static immutable field.
	 *
	 * @param key    The field identifier
	 * @param getter The function used to retrieve the value
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> field(
			@NotNull @Identifier String key,
			@NotNull Function<? extends @NotNull T, ? extends @Nullable Object> getter);

	/**
	 * Registers a non-static mutable field.
	 *
	 * @param key    The field identifier
	 * @param type   The class of the field value
	 * @param getter The function to retrieve the value
	 * @param setter The function to set the value
	 * @param <R>    The type of the field value
	 * @return This type
	 */
	@Contract(value = "_, _, _, _ -> this")
	<R> @NotNull Type<T> field(
			@NotNull @Identifier String key,
			@NotNull Class<R> type,
			@NotNull Function<? extends @NotNull T, ? extends @Nullable R> getter,
			@NotNull BiConsumer<? extends @NotNull T, ? extends @Nullable R> setter);

	// === Static =====================================================

	/**
	 * Registers a set of static methods.
	 *
	 * @param methods The methods to register
	 * @return This type
	 * @see StaticMethods
	 */
	@Contract(value = "_ -> this")
	@NotNull Type<T> methods(@NotNull StaticMethods methods);

	/**
	 * Registers a static void method.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> method(
			@NotNull @Identifier String key,
			@NotNull Runnable function);

	/**
	 * Alias for {@link #method(String, Runnable)}.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	default @NotNull Type<T> action(
			@NotNull @Identifier String key,
			@NotNull Runnable function) {
		return method(key, function);
	}

	/**
	 * Registers a static non-void method.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> method(
			@NotNull @Identifier String key,
			@NotNull Supplier<? extends @Nullable Object> function);

	/**
	 * Alias for {@link #method(String, Supplier)}.
	 *
	 * @param key      The method identifier
	 * @param function The method logic
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	default @NotNull Type<T> computation(
			@NotNull @Identifier String key,
			@NotNull Supplier<? extends @Nullable Object> function) {
		return method(key, function);
	}

	/**
	 * Registers a static immutable field.
	 *
	 * @param key   The field identifier
	 * @param value The field value
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> field(
			@NotNull @Identifier String key,
			@Nullable Object value);

	/**
	 * Registers a static immutable field.
	 *
	 * @param key    The field identifier
	 * @param getter The field getter
	 * @return This type
	 */
	@Contract(value = "_, _ -> this")
	@NotNull Type<T> field(
			@NotNull @Identifier String key,
			@NotNull Supplier<? extends @Nullable Object> getter);

	/**
	 * Registers a static mutable field.
	 *
	 * @param key    The field identifier
	 * @param type   The field type
	 * @param getter The value getter
	 * @param setter The value setter
	 * @param <V>    The type of the field
	 * @return This type
	 */
	@Contract(value = "_, _, _, _ -> this")
	<V> @NotNull Type<T> field(
			@NotNull @Identifier String key,
			@NotNull Class<V> type,
			@NotNull Supplier<? extends @Nullable V> getter,
			@NotNull Consumer<? extends @Nullable V> setter);

}