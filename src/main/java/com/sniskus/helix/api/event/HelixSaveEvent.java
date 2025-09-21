package com.sniskus.helix.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public final class HelixSaveEvent extends Event {
	private static final HandlerList hL = new HandlerList();

	@Override
	public @NotNull HandlerList getHandlers() {
		return hL;
	}
	
	public static @NotNull HandlerList getHandlerList() {
		return hL;
	}

}