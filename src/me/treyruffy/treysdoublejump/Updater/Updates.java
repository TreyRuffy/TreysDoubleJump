package me.treyruffy.treysdoublejump.Updater;

import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.Util.ConfigManager;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 02/1/2020.
 */

public class Updates implements Listener {

	// Sends a console message if an update is available 
	public static void updateCheck(){
		if (ConfigManager.getConfig().getBoolean("Updates.Check")){
			
			String latestUpdate = UpdateChecker.request("19630", Bukkit.getPluginManager().getPlugin("TreysDoubleJump").getDescription().getName() + " v" + Bukkit.getPluginManager().getPlugin("TreysDoubleJump").getDescription().getVersion());
			if (latestUpdate == "") {
				return;
			}
			int latestUpdateVersion = Integer.parseInt(latestUpdate.replace(".", ""));
			int versionOn = Integer.parseInt(Bukkit.getPluginManager().getPlugin("TreysDoubleJump").getDescription().getVersion().replace(".", ""));
			if (latestUpdateVersion <= versionOn) {
				return;
			}
			
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			console.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
			console.sendMessage(ChatColor.GREEN + "There is a new update (" + latestUpdate + ") for");
			console.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
			console.sendMessage(ChatColor.RED + "Download at:");
			console.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
			console.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
			
			
			if (ConfigManager.getConfig().getBoolean("Updates.TellPlayers")){
				for (Player p : Bukkit.getOnlinePlayers()){
					if (p.hasPermission("tdj.updates")){
						p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
						p.sendMessage(ChatColor.GREEN + "There is a new update (" + latestUpdate + ") for");
						p.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
						p.sendMessage(ChatColor.RED + "Download at:");
						p.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
						p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
					}
				}
			}
		}
	}
	
	// Sends the player with permissions a message if an update is available  
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if (ConfigManager.getConfig().getBoolean("Updates.Check")){
			return;
		}
		if (!ConfigManager.getConfig().getBoolean("Updates.TellPlayers")){
			return;
		}
		String latestUpdate = UpdateChecker.request("19630", Bukkit.getPluginManager().getPlugin("TreysDoubleJump").getDescription().getName() + " v" + Bukkit.getPluginManager().getPlugin("TreysDoubleJump").getDescription().getVersion());
		if (latestUpdate == "") {
			return;
		}
		int latestUpdateVersion = Integer.parseInt(latestUpdate.replace(".", ""));
		int versionOn = Integer.parseInt(Bukkit.getPluginManager().getPlugin("TreysDoubleJump").getDescription().getVersion().replace(".", ""));
		if (latestUpdateVersion <= versionOn) {
			return;
		}
		
		
		final Player p = e.getPlayer();
		
		if (!p.hasPermission("tdj.updates")){
			return;
		}
		
		p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
		p.sendMessage(ChatColor.GREEN + "There is a new update (" + latestUpdate + ") for");
		p.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
		p.sendMessage(ChatColor.RED + "Download at:");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
		p.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
				
	}
	
}
