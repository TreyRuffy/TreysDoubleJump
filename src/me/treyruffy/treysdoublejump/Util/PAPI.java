package me.treyruffy.treysdoublejump.Util;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.API.DoubleJump;
import me.treyruffy.treysdoublejump.API.Flight;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 01/26/2020.
 */

public class PAPI extends PlaceholderExpansion {

	private TreysDoubleJump plugin;
	
	// Registers the tdj placeholder
	public PAPI(TreysDoubleJump plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean persist() {
		return true;
	}

	@Override
	public boolean canRegister() {
		return true;
	}
	
	// Registers the placeholders
	public String onPlaceholderRequest(Player p, String identifier) {
		if (p == null){
			return "";
		}
		if (identifier.equalsIgnoreCase("cooldown")){
			return DoubleJump.getDoubleJumpTime(p).toString();
		}
		if (identifier.equalsIgnoreCase("flightenabled")) {
			return Flight.isFlightEnabled(p).toString();
		}
		if (identifier.equalsIgnoreCase("doublejumpenabled")) {
			return DoubleJump.isDoubleJumpEnabled(p).toString();
		}
		if (identifier.equalsIgnoreCase("groundpoundenabled")) {
			return DoubleJump.isGroundPoundEnabled(p).toString();
		}
		if (identifier.equalsIgnoreCase("canusegroundpound")) {
			return DoubleJump.canUseGroundPound(p).toString();
		}
		
		return null;
	}

	@Override
	public String getAuthor() {
		return plugin.getDescription().getAuthors().toString();
	}

	@Override
	public String getIdentifier() {
		return "tdj";
	}

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

}
