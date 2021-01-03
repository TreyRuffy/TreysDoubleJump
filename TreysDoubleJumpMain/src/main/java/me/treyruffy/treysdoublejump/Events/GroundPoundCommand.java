package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;

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

public class GroundPoundCommand implements CommandExecutor {

	// Players in this list cannot ground pound
	public static ArrayList<String> groundPoundDisabled = new ArrayList<>();
	
	// Sets all the /groundpound commands
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("groundpound")) {
			if (ConfigManager.getConfig().getBoolean("GroundPound.Enabled")){
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (p.hasPermission("tdj.groundpoundcommand")) {
						if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains((p).getWorld().getName())){
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.NotInWorld")));
							return true;
						}
						if (groundPoundDisabled.contains(p.getUniqueId().toString())) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.GroundPoundToggledOn")));
							groundPoundDisabled.remove(p.getUniqueId().toString());
						} else {
							DoubleJump.Grounded.remove(p.getUniqueId().toString());
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.GroundPoundToggledOff")));
							groundPoundDisabled.add(p.getUniqueId().toString());
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.NoPermission")));
					}
					return true;
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.PlayersOnly")));
				return true;
			}
		}
		return true;
	}
}
