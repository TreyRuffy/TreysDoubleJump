package me.treyruffy.treysdoublejump.api;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/** The type Double jump event. */
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

  /**
   * Instantiates a new Double jump event.
   *
   * @param player the player
   * @param cooldownEnabled the cooldown enabled
   * @param cooldownTime the cooldown time
   * @param velocityForward the velocity forward
   * @param velocityUp the velocity up
   * @param soundsEnabled the sounds enabled
   * @param sound the sound
   * @param volume the volume
   * @param pitch the pitch
   * @param particlesEnabled the particles enabled
   * @param particlesForEveryone the particles for everyone
   * @param particleType the particle type
   * @param particleAmount the particle amount
   * @param r the r
   * @param g the g
   * @param b the b
   */
  public DoubleJumpEvent(
      Player player,
      boolean cooldownEnabled,
      int cooldownTime,
      double velocityForward,
      double velocityUp,
      boolean soundsEnabled,
      Sound sound,
      float volume,
      float pitch,
      boolean particlesEnabled,
      boolean particlesForEveryone,
      String particleType,
      int particleAmount,
      float r,
      float g,
      float b) {
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

  /**
   * Gets handler list.
   *
   * @return the handler list
   */
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

  /**
   * Gets player.
   *
   * @return the player
   */
  public Player getPlayer() {
		return this.player;
	}

  /**
   * Is cooldown enabled boolean.
   *
   * @return cooldown enabled boolean
   */
  public boolean isCooldownEnabled() {
		return this.cooldownEnabled;
	}

  /**
   * Sets cooldown time.
   *
   * @param cooldownTime the cooldown time
   */
  public void setCooldownTime(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

  /**
   * Gets cooldown time.
   *
   * @return the cooldown time
   */
  public int getCooldownTime() {
		return this.cooldownTime;
	}

  /**
   * Sets cooldown enabled.
   *
   * @param cooldownEnabled the cooldown enabled
   */
  public void setCooldownEnabled(boolean cooldownEnabled) {
		this.cooldownEnabled = cooldownEnabled;
	}

  /**
   * Sets velocity forward.
   *
   * @param velocityForward the velocity forward
   */
  public void setVelocityForward(double velocityForward) {
		this.velocityForward = velocityForward;
	}

  /**
   * Gets velocity forward.
   *
   * @return the velocity forward
   */
  public double getVelocityForward() {
		return this.velocityForward;
	}

  /**
   * Sets velocity up.
   *
   * @param velocityUp the velocity up
   */
  public void setVelocityUp(double velocityUp) {
		this.velocityUp = velocityUp;
	}

  /**
   * Gets velocity up.
   *
   * @return the velocity up
   */
  public double getVelocityUp() {
		return this.velocityUp;
	}

  /**
   * Sounds enabled boolean.
   *
   * @return gets sounds enabled
   */
  public boolean soundsEnabled() {
		return this.soundsEnabled;
	}

  /**
   * Sets sounds enabled.
   *
   * @param soundsEnabled the sounds enabled
   */
  public void setSoundsEnabled(boolean soundsEnabled) {
		this.soundsEnabled = soundsEnabled;
	}

  /**
   * Gets sound.
   *
   * @return gets the sound
   */
  public Sound getSound() {
		return this.sound;
	}

  /**
   * Sets sound.
   *
   * @param sound the sound
   */
  public void setSound(Sound sound) {
		this.sound = sound;
	}

  /**
   * Gets volume.
   *
   * @return gets the volume
   */
  public float getVolume() {
		return this.volume;
	}

  /**
   * Sets volume.
   *
   * @param volume the volume
   */
  public void setVolume(float volume) {
		this.volume = volume;
	}

  /**
   * Gets pitch.
   *
   * @return gets the pitch
   */
  public float getPitch() {
		return this.pitch;
	}

  /**
   * Sets pitch.
   *
   * @param pitch the pitch
   */
  public void setPitch(float pitch) {
		this.pitch = pitch;
	}

  /**
   * Particles enabled boolean.
   *
   * @return gets if particles are enabled
   */
  public boolean particlesEnabled() {
		return this.particlesEnabled;
	}

  /**
   * Sets particles enabled.
   *
   * @param particlesEnabled the particles enabled
   */
  public void setParticlesEnabled(boolean particlesEnabled) {
		this.particlesEnabled = particlesEnabled;
	}

  /**
   * Are particles seen for everyone boolean.
   *
   * @return are particles seen for everyone
   */
  public boolean isParticlesForEveryone() {
		return this.particlesForEveryone;
	}

  /**
   * Sets particles seen for everyone.
   *
   * @param particlesForEveryone the particles seen for everyone
   */
  public void setParticlesForEveryone(boolean particlesForEveryone) {
		this.particlesForEveryone = particlesForEveryone;
	}

  /**
   * Gets particle type.
   *
   * @return the particle type as a string
   */
  public String getParticleType() {
		return this.particleType;
	}

  /**
   * Sets particle type.
   *
   * @param particleType the particle type as a string
   */
  public void setParticleType(String particleType) {
		this.particleType = particleType;
	}

  /**
   * Gets particle amount.
   *
   * @return the particle amount
   */
  public int getParticleAmount() {
		return this.particleAmount;
	}

  /**
   * Sets particle amount.
   *
   * @param particleAmount the particle amount
   */
  public void setParticleAmount(int particleAmount) {
		this.particleAmount = particleAmount;
	}

  /**
   * Gets particle's r.
   * As in RGB.
   *
   * @return the particle r
   */
  public float getParticleR() {
		return this.r;
	}

  /**
   * Sets particle's r.
   * As in RGB.
   *
   * @param particleR the particle r
   */
  public void setParticleR(float particleR) {
		this.r = particleR;
	}

  /**
   * Gets particle's g.
   * As in RGB.
   *
   * @return the particle g
   */
  public float getParticleG() {
		return this.g;
	}

  /**
   * Sets particle's g.
   * As in RGB.
   *
   * @param particleG the particle g
   */
  public void setParticleG(float particleG) {
		this.g = particleG;
	}

  /**
   * Gets particle's b.
   * As in RGB.
   *
   * @return the particle b
   */
  public float getParticleB() {
		return this.b;
	}

  /**
   * Sets particle's b.
   * As in RGB.
   *
   * @param particleB the particle b
   */
  public void setParticleB(float particleB) {
		this.b = particleB;
	}
}
