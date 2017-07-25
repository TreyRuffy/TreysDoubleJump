package me.treyruffy.treysdoublejump;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.external.EZPlaceholderHook;

public class PAPI extends EZPlaceholderHook {

	private TreysDoubleJump plugin;
	
	public PAPI(TreysDoubleJump plugin) {
		super(plugin, "tdj");
		this.plugin = plugin;
	}

	@Override
	public String onPlaceholderRequest(Player p, String identifier) {
		if (identifier.equalsIgnoreCase("cooldown")){
			if (plugin.getCooldown(p) == null || plugin.getCooldown(p).equals("0")){
				return String.valueOf("0");
			}			
			return String.valueOf(plugin.getCooldown(p));
		}
		if (p == null){
			return "";
		}
		
		return null;
	}

}
