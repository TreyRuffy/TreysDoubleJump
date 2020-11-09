package me.treyruffy.treysdoublejump.API;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DoubleJumpEvent extends Event implements Cancellable {

	private final Player player;
	private boolean isCancelled;
	private boolean cooldownEnabled;
	private int cooldownTime;
	private double velocityForward;
	private double velocityUp;
	private boolean soundsEnabled;
	private Sound sound;
	private float volume;
	private float pitch;
	private boolean particlesEnabled;
	private boolean particlesForEveryone;
	private String particleType;
	private int particleAmount;
	private float r;
	private float g;
	private float b;

	public DoubleJumpEvent(Player player, boolean cooldownEnabled, int cooldownTime, double velocityForward,
						   double velocityUp, boolean soundsEnabled, Sound sound, float volume, float pitch,
						   boolean particlesEnabled, boolean particlesForEveryone, String particleType,
						   int particleAmount, float r, float g, float b) {
		this.player = player;
		this.cooldownEnabled = cooldownEnabled;
		this.cooldownTime = cooldownTime;
		this.velocityForward = velocityForward;
		this.velocityUp = velocityUp;
		this.soundsEnabled = soundsEnabled;
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.particlesEnabled = particlesEnabled;
		this.particlesForEveryone = particlesForEveryone;
		this.particleType = particleType;
		this.particleAmount = particleAmount;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	private static final HandlerList HANDLERS = new HandlerList();

	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public boolean isCancelled() {
		return this.isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.isCancelled = cancel;
	}

	public Player getPlayer() {
		return this.player;
	}

	public boolean isCooldownEnabled() {
		return this.cooldownEnabled;
	}

	public void setCooldownTime(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

	public int getCooldownTime() {
		return this.cooldownTime;
	}

	public void setCooldownEnabled(boolean cooldownEnabled) {
		this.cooldownEnabled = cooldownEnabled;
	}

	public void setVelocityForward(double velocityForward) {
		this.velocityForward = velocityForward;
	}

	public double getVelocityForward() {
		return this.velocityForward;
	}

	public void setVelocityUp(double velocityUp) {
		this.velocityUp = velocityUp;
	}

	public double getVelocityUp() {
		return this.velocityUp;
	}

	public boolean soundsEnabled() {
		return this.soundsEnabled;
	}

	public void setSoundsEnabled(boolean soundsEnabled) {
		this.soundsEnabled = soundsEnabled;
	}

	public Sound getSound() {
		return this.sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

	public float getVolume() {
		return this.volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getPitch() {
		return this.pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public boolean particlesEnabled() {
		return this.particlesEnabled;
	}

	public void setParticlesEnabled(boolean particlesEnabled) {
		this.particlesEnabled = particlesEnabled;
	}

	public boolean isParticlesForEveryone() {
		return this.particlesForEveryone;
	}

	public void setParticlesForEveryone(boolean particlesForEveryone) {
		this.particlesForEveryone = particlesForEveryone;
	}

	public String getParticleType() {
		return this.particleType;
	}

	public void setParticleType(String particleType) {
		this.particleType = particleType;
	}

	public int getParticleAmount() {
		return this.particleAmount;
	}

	public void setParticleAmount(int particleAmount) {
		this.particleAmount = particleAmount;
	}

	public float getParticleR() {
		return this.r;
	}

	public void setParticleR(float particleR) {
		this.r = particleR;
	}

	public float getParticleG() {
		return this.g;
	}

	public void setParticleG(float particleG) {
		this.g = particleG;
	}

	public float getParticleB() {
		return this.b;
	}

	public void setParticleB(float particleB) {
		this.b = particleB;
	}
}
