package me.treyruffy.treysdoublejump.API;

import me.treyruffy.treysdoublejump.Events.DoubleJump;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Events.DoubleJumpCommand;
import me.treyruffy.treysdoublejump.Events.GroundPoundCommand;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class DoubleJumpAPI {

	// Accesses the cooldown timer for the player
	public static Integer getDoubleJumpTime(Player player) {
		if (DoubleJump.getCooldown(player) == null || DoubleJump.getCooldown(player) == 0){
			return 0;
		} else {
			return DoubleJump.getCooldown(player);
		}
	}
	
	// Accesses whether the player can double jump or not
	public static Boolean isDoubleJumpEnabled(Player player) {
		return !DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString());
	}
	
	// Sets whether the player can double jump or not
	public static void setDoubleJump(Player player, Boolean b) {
		if (b) {
			if (!DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString())) {
				DoubleJumpCommand.DisablePlayers.add(player.getUniqueId().toString());
			}
		} else {
			DoubleJumpCommand.DisablePlayers.remove(player.getUniqueId().toString());
		}
	}
	
	// Accesses whether the player has access to ground pound
	public static Boolean isGroundPoundEnabled(Player player) {
		return !GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString());
	}
	
	// Accesses whether the player can use ground pound now
	public static Boolean canUseGroundPound(Player player) {
		return DoubleJump.Grounded.contains(player.getUniqueId().toString());
	}
	
	// Sets whether the player can or cannot use ground pound
	public static void setGroundPound(Player player, Boolean b) {
		if (!b) {
			if (!GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString())) {
				GroundPoundCommand.groundPoundDisabled.add(player.getUniqueId().toString());
			}
		} else {
			GroundPoundCommand.groundPoundDisabled.remove(player.getUniqueId().toString());
		}
	}
}
