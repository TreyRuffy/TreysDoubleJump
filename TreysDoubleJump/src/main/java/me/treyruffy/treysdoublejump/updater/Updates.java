package me.treyruffy.treysdoublejump.updater;

import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.util.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 02/1/2020.
 */

public class Updates implements Listener {

	static String latestUpdateVersion;
	static boolean notifyAboutUpdate = false;

	// Sends a console message if an update is available 
	public static void updateCheck(){
		if (ConfigManager.getConfig().getBoolean("Updates.Check")){
			
			latestUpdateVersion = UpdateChecker.request("19630",
					TreysDoubleJump.getInstance().getDescription().getName() + " v" + TreysDoubleJump.getInstance().getDescription().getVersion());
			if (latestUpdateVersion.equals("")) {
				return;
			}
			int latestUpdate = Integer.parseInt(latestUpdateVersion.replace(".", ""));
			int versionOn = Integer.parseInt(TreysDoubleJump.getInstance().getDescription().getVersion().replace(".", ""));
			if (latestUpdate <= versionOn) {
				return;
			}
			
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			sendMessage(console, latestUpdateVersion);
			notifyAboutUpdate = true;

			if (ConfigManager.getConfig().getBoolean("Updates.TellPlayers")){
				for (Player p : Bukkit.getOnlinePlayers()){
					if (p.hasPermission("tdj.updates")){
						sendMessage(p, latestUpdateVersion);
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
		if (!notifyAboutUpdate) {
			return;
		}

		final Player p = e.getPlayer();
		
		if (!p.hasPermission("tdj.updates")){
			return;
		}
		
		sendMessage(p, latestUpdateVersion);
				
	}

	private static void sendMessage(CommandSender player, String latestUpdate) {
		player.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
		player.sendMessage(ChatColor.GREEN + "There is a new update (" + latestUpdate + ") for");
		player.sendMessage(ChatColor.GREEN + "Trey's Double Jump");
		player.sendMessage(ChatColor.RED + "Download at:");
		player.sendMessage(ChatColor.LIGHT_PURPLE + "https://www.spigotmc.org/resources/19630/");
		player.sendMessage(ChatColor.AQUA + "+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+");
	}

}
