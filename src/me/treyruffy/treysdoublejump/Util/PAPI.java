package me.treyruffy.treysdoublejump.Util;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.API.DoubleJump;
import me.treyruffy.treysdoublejump.API.Flight;

public class PAPI extends EZPlaceholderHook {

	public PAPI(TreysDoubleJump plugin) {
		super(plugin, "tdj");
	}

	@Override
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

}
