package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.ConfigManager;

public class GroundPoundCommand implements CommandExecutor {

	public static ArrayList<String> groundPoundDisabled = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("groundpound")) {
			if (ConfigManager.getConfig().getBoolean("GroundPound.Enabled")){
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (p.hasPermission("tdj.groundpoundcommand")) {
						if (groundPoundDisabled.contains(p.getUniqueId().toString())) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.GroundPoundToggledOn")));
							groundPoundDisabled.remove(p.getUniqueId().toString());
							return true;
						} else {
							if (DoubleJump.Grounded.contains(p.getUniqueId().toString())) {
								DoubleJump.Grounded.remove(p.getUniqueId().toString());
							}
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.GroundPoundToggledOff")));
							groundPoundDisabled.add(p.getUniqueId().toString());
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
