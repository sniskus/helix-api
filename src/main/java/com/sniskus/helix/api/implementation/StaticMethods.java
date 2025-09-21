package com.sniskus.helix.api.implementation;

/**
 * Marker interface for defining static methods in a namespace.
 * <p>
 * Implementations of this interface should define instance methods,
 * which are then added to a {@link Namespace} as static methods
 * when registered via {@link Namespace#methods(StaticMethods)}.
 * </p>
 * <p>
 * Example usage:
 * {@snippet :
 *
 *
 *  import com.sniskus.helix.api.annotate.arguments.NotNull;
 *  import org.bukkit.Bukkit;
 *  import org.bukkit.entity.Player;
 *
 *  public class MyStaticMethods implements StaticMethods {
 *
 *    public Player getPlayer(@NotNull String name) {
 *      return Bukkit.getPlayer(name);
 *    }
 *  }
 *}
 * </p>
 *
 * @see Namespace#methods(StaticMethods)
 * @see com.sniskus.helix.api.annotate
 */
public interface StaticMethods { }