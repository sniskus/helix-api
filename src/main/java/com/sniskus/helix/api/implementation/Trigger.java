package com.sniskus.helix.api.implementation;

import com.google.common.base.Preconditions;
import com.sniskus.helix.api.HelixApi;
import com.sniskus.helix.api.script.Dictionary;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiPredicate;

/**
 * Represents a trigger which is responsible for activating a {@code trigger} block.
 * <p>
 * A trigger must declare a <b>single public</b> constructor, which may take any number of arguments.
 * These arguments are accessible to the user and are passed to the trigger instance
 * when it is created.
 * </p>
 *
 * @param <T> The type of value associated with this trigger
 * @see com.sniskus.helix.api.annotate.arguments
 * @see Namespace#trigger(String, Class)
 * @see Namespace#trigger(String, Class, BiPredicate)
 */
public abstract class Trigger<T> {
	private final Class<T> typeClass;

	/**
	 * Constructs a new trigger with the specified type.
	 *
	 * @param typeClass The class type of the value associated with this trigger
	 * @throws NullPointerException if {@code typeClass} is null
	 */
	protected Trigger(@NotNull Class<T> typeClass) {
		this.typeClass = Preconditions.checkNotNull(typeClass);
	}

	/**
	 * Retrieves the type class associated with this trigger.
	 *
	 * @return The class type of the trigger value
	 */
	@Contract(pure = true)
	public final @NotNull Class<T> getTypeClass() {
		return typeClass;
	}

	/**
	 * Called when the trigger listener is registered.
	 * <p>
	 * This method can be overridden to perform setup actions.
	 * </p>
	 */
	@ApiStatus.OverrideOnly
	public void onListenerRegister() { }

	/**
	 * Handles the execution of the trigger.
	 * <p>
	 * The handler method processes and validates the provided value and
	 * can modify the given dictionary to set script variables before execution.
	 * </p>
	 *
	 * @param value The value passed when activating this trigger
	 * @param dict  The dictionary used for script execution
	 * @return {@code true} if execution should proceed; {@code false} otherwise
	 */
	@ApiStatus.OverrideOnly
	public boolean handle(T value, @NotNull Dictionary dict) {
		return true;
	}

	/**
	 * Called when the trigger listener is unregistered.
	 * <p>
	 * This method can be overridden to perform cleanup actions when the trigger instance is removed.
	 * </p>
	 */
	@ApiStatus.OverrideOnly
	public void onListenerUnregister() { }

	/**
	 * Convenience method for calling this trigger with the provided value.
	 * Delegates to {@link EventBus#post(Trigger, Object)}
	 *
	 * @param with The value to pass when activating the trigger
	 */
	public final void call(T with) {
		HelixApi.getApi().getEventBus().post(this, with);
	}

	/**
	 * Represents a trigger that does not require a value when activated.
	 */
	public static abstract class Simple extends Trigger<Void> {

		/**
		 * Constructs a new simple trigger instance.
		 */
		protected Simple() {
			super(Void.class);
		}

		/**
		 * Handles the execution of the trigger. Delegates to {@link Simple#handle(Dictionary)}.
		 *
		 * @param value Ignored (always {@code null})
		 * @param dict  The dictionary used for script execution
		 * @return {@code true} if execution should proceed; {@code false} otherwise
		 * @see Simple#handle(Dictionary)
		 */
		@Override
		@ApiStatus.Internal
		public final boolean handle(Void value, @NotNull Dictionary dict) {
			return handle(dict);
		}

		/**
		 * Handles the execution of the trigger.
		 * <p>
		 * Since this trigger does not receive a value, this method is mainly meant
		 * to store any necessary script variables before execution.
		 * </p>
		 *
		 * @return {@code true} if execution should proceed; {@code false} otherwise
		 */
		@ApiStatus.OverrideOnly
		public boolean handle(@NotNull Dictionary ignored) {
			return true;
		}

		/**
		 * Convenience method for calling this trigger.
		 * Delegates to {@link Trigger#call(Object)} with {@code null} as
		 * the value object.
		 */
		public final void call() {
			call(null);
		}

	}

}
