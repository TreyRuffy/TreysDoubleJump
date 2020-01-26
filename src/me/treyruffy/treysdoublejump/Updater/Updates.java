package me.treyruffy.treysdoublejump.Updater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.Util.ConfigManager;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 01/26/2020.
 */

public class Updates implements Listener {

	// Sends a console message if an update is available 
	public static void updateCheck(){
		if (ConfigManager.getConfig().getBoolean("Updates.Check")){
			UpdateChecker.getLastUpdate(TreysDoubleJump.getPlugin(TreysDoubleJump.class));
			String updates = UpdateChecker.getLastUpdate(TreysDoubleJump.getPlugin(TreysDoubleJump.class));
			if (!updates.contains("")){
				ConsoleCommandSender console = Bukkit.getConsoleSender();
				console.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
				console.sendMessage(ChatColor.GREEN + "There is a new update for");
				console.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
				console.sendMessage(ChatColor.RED + "Download at:");
				console.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
				console.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
				if (ConfigManager.getConfig().getBoolean("Updates.TellPlayers")){
					for (Player p : Bukkit.getOnlinePlayers()){
						if (p.hasPermission("tdj.updates")){
							p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
							p.sendMessage(ChatColor.GREEN + "There is a new update for");
							p.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
							p.sendMessage(ChatColor.RED + "Download at:");
							p.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
							p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
						}
					}
				}
			}
		}
	}
	
	// Sends the player with permissions a message if an update is available  
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if (ConfigManager.getConfig().getBoolean("Updates.Check")){
			if (ConfigManager.getConfig().getBoolean("Updates.TellPlayers")){
				final Player p = e.getPlayer();
				if (p.hasPermission("tdj.updates")){
					String updates = UpdateChecker.getLastUpdate(TreysDoubleJump.getPlugin(TreysDoubleJump.class));
					if (!updates.contains("")){
						Bukkit.getScheduler().runTaskAsynchronously(TreysDoubleJump.getPlugin(TreysDoubleJump.class), new Runnable() {
							@Override
							public void run() {
								p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
								p.sendMessage(ChatColor.GREEN + "There is a new update for");
								p.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
								p.sendMessage(ChatColor.RED + "Download at:");
								p.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
								p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
							}
						});
					}
				}
			}
		}
	}
	
}
