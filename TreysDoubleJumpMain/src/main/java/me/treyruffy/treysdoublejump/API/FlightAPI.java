package me.treyruffy.treysdoublejump.API;

import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Events.FlightCommand;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class FlightAPI {

	public static Boolean isFlightEnabled(Player player) {
		return FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString());
	}
	
	public static void setFlight(Player player, Boolean b) {
		if (b) {
			if (!FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString())) {
				FlightCommand.FlyingPlayers.add(player.getUniqueId().toString());
			}
		} else {
			FlightCommand.FlyingPlayers.remove(player.getUniqueId().toString());
		}
	}
	
}
