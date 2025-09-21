package com.sniskus.helix.api.implementation;

import org.jetbrains.annotations.NotNull;

/**
 * Provides a base class for instance methods that operate on a delegated object.
 * <p>
 * Subclasses of {@code InstanceMethods} define instance methods that interact with an underlying
 * delegate object of type {@code T}. Any implementing class must declare a single public constructor,
 * which only takes the delegate as an argument.
 * </p>
 * <p>
 * Example usage:
 * {@snippet :
 *
 * import com.sniskus.helix.api.annotate.arguments.NotNull;
 * import org.bukkit.entity.Player;
 *
 * public class MyInstanceMethods extends InstanceMethods<Player> {
 *
 *   public MyInstanceMethods(Player delegate) {
 *     super(delegate);
 *   }
 *
 *   public boolean hasPermission(@NotNull String perm) {
 *     return delegate.hasPermission(perm);
 *   }
 * }
 *}
 * </p>
 *
 * @param <T> The type of the delegate object
 * @see Type#methods(Class)
 * @see com.sniskus.helix.api.annotate
 */
public abstract class InstanceMethods<T> {

	/**
	 * The delegate object on which this instance operate.
	 */
	protected final @NotNull T delegate;

	/**
	 * Constructs an instance of {@code InstanceMethods} with the specified delegate.
	 *
	 * @param delegate The delegate object to be used by the instance
	 */
	protected InstanceMethods(@NotNull T delegate) {
		this.delegate = delegate;
	}

}