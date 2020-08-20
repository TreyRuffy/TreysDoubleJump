package me.treyruffy.treysdoublejump.Util;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import me.treyruffy.treysdoublejump.API.DoubleJumpAPI;
import me.treyruffy.treysdoublejump.API.FlightAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class MVdWPAPI {

	// Registers the MVdW placeholders
	public static void register(Plugin plugin) {
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_cooldown", e -> {
			if (e.isOnline()) {
				Player p = e.getPlayer();
				return DoubleJumpAPI.getDoubleJumpTime(p).toString();
			} else {
				return "Players Only!";
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_flightenabled", e -> {
			if (e.isOnline()) {
				Player p = e.getPlayer();
				return FlightAPI.isFlightEnabled(p).toString();
			} else {
				return "Players Only!";
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_doublejumpenabled", e -> {
			if (e.isOnline()) {
				Player p = e.getPlayer();
				return DoubleJumpAPI.isDoubleJumpEnabled(p).toString();
			} else {
				return "Players Only!";
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_groundpoundenabled", e -> {
			if (e.isOnline()) {
				Player p = e.getPlayer();
				return DoubleJumpAPI.isGroundPoundEnabled(p).toString();
			} else {
				return "Players Only!";
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_canusegroundpound", e -> {
			if (e.isOnline()) {
				Player p = e.getPlayer();
				return DoubleJumpAPI.canUseGroundPound(p).toString();
			} else {
				return "Players Only!";
			}
		});
	}
	
}
