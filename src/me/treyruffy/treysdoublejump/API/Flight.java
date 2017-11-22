package me.treyruffy.treysdoublejump.API;

import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Events.FlightCommand;

public class Flight {

	public static Boolean isFlightEnabled(Player player) {
		if (FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void setFlight(Player player, Boolean b) {
		if (b) {
			if (!FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString())) {
				FlightCommand.FlyingPlayers.add(player.getUniqueId().toString());
			}
		} else {
			if (FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString())) {
				FlightCommand.FlyingPlayers.remove(player.getUniqueId().toString());
			}
		}
		return;
	}
	
}
