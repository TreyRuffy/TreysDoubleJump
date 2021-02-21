package me.treyruffy.treysdoublejump.API;

import me.treyruffy.treysdoublejump.Events.DoubleJump;
import me.treyruffy.treysdoublejump.Events.DoubleJumpCommand;
import me.treyruffy.treysdoublejump.Events.GroundPoundCommand;
import org.bukkit.entity.Player;

/** Created by TreyRuffy on 08/12/2018. */
public class DoubleJumpAPI {

  /**
   * Gets the double jump cooldown time for the player.
   * Returns 0 if it is null.
   *
   * @param player the player
   * @return the cooldown time in seconds
   */
  // Accesses the cooldown timer for the player
  public static Integer getDoubleJumpTime(Player player) {
		if (DoubleJump.getCooldown(player) == null || DoubleJump.getCooldown(player) == 0){
			return 0;
		} else {
			return DoubleJump.getCooldown(player);
		}
	}

  /**
   * Checks if double jumping is enabled for the player.
   * Returns true if the player can double jump.
   *
   * @param player the player
   * @return double jump enabled for the player
   */
  // Accesses whether the player can double jump or not
  public static Boolean isDoubleJumpEnabled(Player player) {
		return !DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString());
	}

  /**
   * Sets double jump.
   *
   * @param player the player
   * @param enabled sets double jump to enabled if true
   */
  // Sets whether the player can double jump or not
  public static void setDoubleJump(Player player, Boolean enabled) {
		if (!enabled) {
			if (!DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString())) {
				DoubleJumpCommand.DisablePlayers.add(player.getUniqueId().toString());
			}
		} else {
			DoubleJumpCommand.DisablePlayers.remove(player.getUniqueId().toString());
		}
	}

  /**
   * Is ground pound enabled.
   *
   * @param player the player
   * @return ground pound enabled for the player
   */
  // Accesses whether the player has access to ground pound
  public static Boolean isGroundPoundEnabled(Player player) {
		return !GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString());
	}

  /**
   * Can use ground pound now.
   * Returns true if the player can use ground pound now.
   *
   * @param player the player
   * @return can use ground pound now
   */
  // Accesses whether the player can use ground pound now
  public static Boolean canUseGroundPound(Player player) {
		return DoubleJump.Grounded.contains(player.getUniqueId().toString());
	}

  /**
   * Sets ground pound.
   *
   * @param player the player
   * @param enabled sets the ground pound to enabled if true
   */
  // Sets whether the player can or cannot use ground pound
  public static void setGroundPound(Player player, Boolean enabled) {
		if (!enabled) {
			if (!GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString())) {
				GroundPoundCommand.groundPoundDisabled.add(player.getUniqueId().toString());
			}
		} else {
			GroundPoundCommand.groundPoundDisabled.remove(player.getUniqueId().toString());
		}
	}
}
