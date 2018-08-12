package me.treyruffy.treysdoublejump.Util;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.treyruffy.treysdoublejump.API.DoubleJump;
import me.treyruffy.treysdoublejump.API.Flight;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class MVdWPAPI {

	// Registers the MVdW placeholders
	public static void register(Plugin plugin) {
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_cooldown", new PlaceholderReplacer() {
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				if (e.isOnline()) {
					Player p = e.getPlayer();
					return DoubleJump.getDoubleJumpTime(p).toString();
				} else {
					return "Players Only!";
				}
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_flightenabled", new PlaceholderReplacer() {
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				if (e.isOnline()) {
					Player p = e.getPlayer();
					return Flight.isFlightEnabled(p).toString();
				} else {
					return "Players Only!";
				}
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_doublejumpenabled", new PlaceholderReplacer() {
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				if (e.isOnline()) {
					Player p = e.getPlayer();
					return DoubleJump.isDoubleJumpEnabled(p).toString();
				} else {
					return "Players Only!";
				}
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_groundpoundenabled", new PlaceholderReplacer() {
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				if (e.isOnline()) {
					Player p = e.getPlayer();
					return DoubleJump.isGroundPoundEnabled(p).toString();
				} else {
					return "Players Only!";
				}
			}
		});
		PlaceholderAPI.registerPlaceholder(plugin, "tdj_canusegroundpound", new PlaceholderReplacer() {
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				if (e.isOnline()) {
					Player p = e.getPlayer();
					return DoubleJump.canUseGroundPound(p).toString();
				} else {
					return "Players Only!";
				}
			}
		});
	}
	
}
