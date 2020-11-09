package me.treyruffy.treysdoublejump.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PreDoubleJumpEvent extends Event implements Cancellable {

	private final Player player;
	private boolean isCancelled;

	public PreDoubleJumpEvent(Player player, boolean isCancelled) {
		this.player = player;
		this.isCancelled = isCancelled;
	}

	private static final HandlerList HANDLERS = new HandlerList();

	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.isCancelled = cancel;
	}
}
