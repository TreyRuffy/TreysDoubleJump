package me.treyruffy.treysdoublejump.API;

import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Events.DoubleJumpCommand;
import me.treyruffy.treysdoublejump.Events.GroundPoundCommand;

public class DoubleJump {

	public static Integer getDoubleJumpTime(Player player) {
		if (me.treyruffy.treysdoublejump.Events.DoubleJump.getCooldown(player) == null || me.treyruffy.treysdoublejump.Events.DoubleJump.getCooldown(player) == 0){
			return 0;
		} else {
			return me.treyruffy.treysdoublejump.Events.DoubleJump.getCooldown(player);
		}
	}
	
	public static Boolean isDoubleJumpEnabled(Player player) {
		if (DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString())) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void setDoubleJump(Player player, Boolean b) {
		if (b) {
			if (!DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString())) {
				DoubleJumpCommand.DisablePlayers.add(player.getUniqueId().toString());
			}
		} else {
			if (DoubleJumpCommand.DisablePlayers.contains(player.getUniqueId().toString())) {
				DoubleJumpCommand.DisablePlayers.remove(player.getUniqueId().toString());
			}
		}
		return;
	}
	
	public static Boolean isGroundPoundEnabled(Player player) {
		if (GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString())) {
			return false;
		} else {
			return true;
		}
	}
	
	public static Boolean canUseGroundPound(Player player) {
		if (me.treyruffy.treysdoublejump.Events.DoubleJump.Grounded.contains(player.getUniqueId().toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void setGroundPound(Player player, Boolean b) {
		if (!b) {
			if (!GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString())) {
				GroundPoundCommand.groundPoundDisabled.add(player.getUniqueId().toString());
			}
		} else {
			if (GroundPoundCommand.groundPoundDisabled.contains(player.getUniqueId().toString())) {
				GroundPoundCommand.groundPoundDisabled.remove(player.getUniqueId().toString());
			}
		}
		return;
	}
}
