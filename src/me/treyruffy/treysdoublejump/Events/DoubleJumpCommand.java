package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.ConfigManager;

public class DoubleJumpCommand implements CommandExecutor {

	public static List<String> DisablePlayers = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tdj")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains((p).getWorld().getName())){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.NotInWorld")));
					return true;
				}
				if (p.hasPermission("tdj.command")) {
					if (DisablePlayers.contains(p.getUniqueId().toString())) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.ToggledOn")));
						DisablePlayers.remove(p.getUniqueId().toString());
						return true;
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.ToggledOff")));
						DisablePlayers.add(p.getUniqueId().toString());
						p.setFlying(false);
						p.setAllowFlight(false);
						return true;
					}
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.NoPermission")));
					return true;
				}
			}
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.PlayersOnly")));
			return true;
		} else if (cmd.getName().equalsIgnoreCase("djreload")){
			if (sender.hasPermission("tdj.reload")){
				sender.sendMessage(ChatColor.BLUE + "-=====[" + ChatColor.AQUA + ChatColor.BOLD + " TDJ " + ChatColor.BLUE + "]=====-");
				sender.sendMessage(ChatColor.GREEN + "Reloading the double jump YAML files...");
				try {
					ConfigManager.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "Reloaded the double jump YAML files successfully!");
					sender.sendMessage(ChatColor.BLUE + "-======================-");
				} catch (Exception e){
					sender.sendMessage(ChatColor.RED + "Could not reload the double jump YAML files.");
					sender.sendMessage(ChatColor.BLUE + "-======================-");
					e.printStackTrace();
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.NoPermission")));
				return true;
			}
		}
		return true;
	}
	
}
