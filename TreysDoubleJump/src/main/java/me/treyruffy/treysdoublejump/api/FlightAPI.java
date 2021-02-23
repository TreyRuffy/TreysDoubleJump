package me.treyruffy.treysdoublejump.api;

import me.treyruffy.treysdoublejump.events.FlightCommand;
import org.bukkit.entity.Player;

/* Created by TreyRuffy on 08/12/2018. */

/** The Flight api */
public class FlightAPI {

  /**
   * Is flight enabled for the player.
   *
   * @param player the player
   * @return flight enabled for the player
   */
  public static Boolean isFlightEnabled(Player player) {
		return FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString());
	}

  /**
   * Sets flight for the player.
   *
   * @param player the player
   * @param enabled sets flight to enabled if true
   */
  public static void setFlight(Player player, Boolean enabled) {
		if (!enabled) {
			if (!FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString())) {
				FlightCommand.FlyingPlayers.add(player.getUniqueId().toString());
			}
		} else {
			FlightCommand.FlyingPlayers.remove(player.getUniqueId().toString());
		}
	}
	
}
