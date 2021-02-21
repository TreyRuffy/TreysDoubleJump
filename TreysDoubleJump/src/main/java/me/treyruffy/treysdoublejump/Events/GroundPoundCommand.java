package me.treyruffy.treysdoublejump.Events;

import me.treyruffy.treysdoublejump.Util.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
							p.sendMessage(ConfigManager.getConfigMessage("NotInWorld"));
							return true;
						}
						if (groundPoundDisabled.contains(p.getUniqueId().toString())) {
							p.sendMessage(ConfigManager.getConfigMessage("GroundPoundToggledOn"));
							groundPoundDisabled.remove(p.getUniqueId().toString());
						} else {
							DoubleJump.Grounded.remove(p.getUniqueId().toString());
							p.sendMessage(ConfigManager.getConfigMessage("GroundPoundToggledOff"));
							groundPoundDisabled.add(p.getUniqueId().toString());
						}
					} else {
						p.sendMessage(ConfigManager.getConfigMessage("NoPermission"));
					}
					return true;
				}
				sender.sendMessage(ConfigManager.getConfigMessage("PlayersOnly"));
				return true;
			}
		}
		return true;
	}
}
