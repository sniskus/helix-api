package com.sniskus.helix.api.implementation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The internal trigger-to-listener event bus used for activating trigger blocks.
 */
public interface EventBus {

	/**
	 * Posts an object to the event bus. All listeners with a type assignable
	 * from the posted object are invoked.
	 *
	 * @param object The object to post.
	 */
	void post(@NotNull Object object);

	/**
	 * Manually invokes the given trigger and executes all currently registered listeners.
	 *
	 * @param trigger The trigger to activate.
	 * @param with    The argument to pass to the trigger when executing.
	 *                May be {@code null} only if the trigger implementation explicitly allows it.
	 * @param <T>     The type of argument expected by the trigger.
	 */
	<T> void post(@NotNull Trigger<? super T> trigger, @Nullable T with);

}