package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Util.ConfigManager;
import org.jetbrains.annotations.NotNull;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class FlightCommand implements CommandExecutor {

	// Players in this list can fly
	public static List<String> FlyingPlayers = new ArrayList<>();
	
	// Sets all commands for /fly
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
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
							DoubleJump.Grounded.remove(p.getUniqueId().toString());
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
