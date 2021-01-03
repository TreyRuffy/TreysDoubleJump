package me.treyruffy.treysdoublejump.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GroundPoundEvent extends Event implements Cancellable {

	private final Player player;
	private boolean isCancelled;
	private double velocityDown;

	public GroundPoundEvent(Player player, boolean isCancelled, double velocityDown) {
		this.player = player;
		this.isCancelled = isCancelled;
		this.velocityDown = velocityDown;
	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.isCancelled = cancel;
	}

	public boolean getCancelled() {
		return this.isCancelled;
	}

	public double getVelocityDown() {
		return this.velocityDown;
	}

	public void setVelocityDown(double velocityDown) {
		this.velocityDown = velocityDown;
	}

	private static final HandlerList HANDLERS = new HandlerList();

	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
