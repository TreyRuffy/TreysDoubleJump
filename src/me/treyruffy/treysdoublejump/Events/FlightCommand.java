package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.ConfigManager;

public class FlightCommand implements CommandExecutor {

	public static List<String> FlyingPlayers = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (ConfigManager.getConfig().getBoolean("Flight.Enabled")){
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (p.hasPermission("tdj.fly")) {
						if (FlyingPlayers.contains(p.getUniqueId().toString())) {
							p.setFallDistance(0f);
							p.setAllowFlight(false);
							p.setFlying(false);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.FlyToggledOff")));
							FlyingPlayers.remove(p.getUniqueId().toString());
							return true;
						} else {
							p.setFallDistance(0f);
							p.setAllowFlight(true);
							p.setFlying(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.FlyToggledOn")));
							FlyingPlayers.add(p.getUniqueId().toString());
							return true;
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.NoPermission")));
						return true;
					}
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.PlayersOnly")));
				return true;
			}
		}
		return true;
	}
	
}
