package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Util.ConfigManager;
import me.treyruffy.treysdoublejump.Util.UpdateManager;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class DoubleJumpCommand implements CommandExecutor {

	// Players in this list cannot use double jump
	public static List<String> DisablePlayers = new ArrayList<String>();
	
	// All the commands for /tdj
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
						if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
							return true;
						}
						p.setAllowFlight(true);
						return true;
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Messages.ToggledOff")));
						DisablePlayers.add(p.getUniqueId().toString());
						if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
							return true;
						}
						if (!FlightCommand.FlyingPlayers.contains(p.getUniqueId().toString())) {
							p.setFlying(false);
							p.setAllowFlight(false);
						}
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
					new UpdateManager().setup();
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
