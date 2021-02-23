package me.treyruffy.treysdoublejump.events;

import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.util.ConfigManager;
import me.treyruffy.treysdoublejump.util.UpdateManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class DoubleJumpCommand implements CommandExecutor {

	// Players in this list cannot use double jump
	public static List<String> DisablePlayers = new ArrayList<>();
	
	// All the commands for /tdj
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tdj")) {
			if (args.length >= 1) {
				// /tdj enable
				if (args[0].equalsIgnoreCase("enable")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						if (checkWorldAndPerm(p))
							return true;
						if (addEnabledPlayer(p)) {
							TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage("ToggledOn"));
						}
					} else {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
								"PlayersOnly"));
						return true;
					}
				}
				// /tdj disable
				else if (args[0].equalsIgnoreCase("disable")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						if (checkWorldAndPerm(p))
							return true;
						if (addDisabledPlayer(p)) {
							TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
									"ToggledOff"));
						}
					} else {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage("PlayersOnly"));
						return true;
					}
				}
				// /tdj <username>
				else if (Bukkit.getPlayer(args[0]) != null) {

					if (!sender.hasPermission("tdj.toggleothers")) {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
								"NoPermission"));
						return true;
					}

					Player username = Bukkit.getPlayer(args[0]);
					assert username != null;

					// Player is not online
					if (!username.isOnline()) {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
								"PlayerNotFound").replaceText("[user]", Component.text(args[0])));
						return true;
					}
					else if (args.length >= 2) {
						// /tdj <username> enable
						if (args[1].equalsIgnoreCase("enable")) {
							if (addEnabledPlayer(username)) {
								TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
										"ToggledOnOther").replaceText("[user]", Component.text(username.getName())));
								if (!LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
										"DoubleJumpToggledOn")).equalsIgnoreCase(""))
									TreysDoubleJump.adventure().player(username).sendMessage(ConfigManager.getConfigMessage(
											"DoubleJumpToggledOn"));
							}
						}
						// /tdj <username> disable
						else if (args[1].equalsIgnoreCase("disable")) {
							turnDJOff(sender, username);
						}
						// invalid argument after /tdj <username>
						else {
							TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
									"InvalidArgumentWithOther"));
							return true;
						}
					}
					// /tdj <username>
					else {
						if (DisablePlayers.contains(username.getUniqueId().toString()) || FlightCommand.FlyingPlayers.contains(username.getUniqueId().toString())) {
							if (addEnabledPlayer(username)) {
								TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
										"ToggledOnOther").replaceText("[user]", Component.text(username.getName())));
								if (!LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
										"DoubleJumpToggledOff")).equalsIgnoreCase(""))
									TreysDoubleJump.adventure().player(username).sendMessage(ConfigManager.getConfigMessage(
											"DoubleJumpToggledOn"));
							}
						} else {
							turnDJOff(sender, username);
						}
					}
				}
				// invalid argument
				else {
					if (!(sender instanceof Player)) {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
								"PlayerNotFound").replaceText("[user]", Component.text(args[0])));
						return true;
					}
					if (!sender.hasPermission("tdj.toggleothers")) {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
								"InvalidArgument"));
						return true;
					}
					TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
							"PlayerNotFound").replaceText("[user]", Component.text(args[0])));
					return true;
				}
				return true;
			}
			else if (sender instanceof Player) {
				Player p = (Player) sender;
				if (checkWorldAndPerm(p))
					return true;
				if (DisablePlayers.contains(p.getUniqueId().toString()) || FlightCommand.FlyingPlayers.contains(p.getUniqueId().toString())) {
					if (addEnabledPlayer(p))
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
								"ToggledOn"));
				} else {
					if (addDisabledPlayer(p)) {
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
								"ToggledOff"));
					}
				}
				return true;
			}
			TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
					"InvalidArgumentConsole"));
			return true;
		} else if (cmd.getName().equalsIgnoreCase("djreload")){
			if (sender.hasPermission("tdj.reload")){
				sender.sendMessage(ChatColor.BLUE + "-=====[" + ChatColor.AQUA + ChatColor.BOLD + " TDJ " + ChatColor.BLUE + "]=====-");
				sender.sendMessage(ChatColor.GREEN + "Reloading the double jump YAML files...");
				try {
					ConfigManager.reloadConfig();
					new UpdateManager().setup();
					sender.sendMessage(ChatColor.GREEN + "Reloaded the double jump YAML files successfully!");
					sender.sendMessage(ChatColor.BLUE + "-======================-");
				} catch (Exception e){
					sender.sendMessage(ChatColor.RED + "Could not reload the double jump YAML files.");
					sender.sendMessage(ChatColor.BLUE + "-======================-");
					e.printStackTrace();
				}
			} else {
				TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
						"NoPermission"));
			}
			return true;
		}
		return true;
	}

	private void turnDJOff(@NotNull CommandSender sender, Player username) {
		if (addDisabledPlayer(username)) {
			TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
					"ToggledOffOther").replaceText("[user]",
					Component.text(username.getName())));
			if (!LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
					"DoubleJumpToggledOff")).equalsIgnoreCase(""))
				TreysDoubleJump.adventure().player(username).sendMessage(ConfigManager.getConfigMessage(
						"DoubleJumpToggledOff"));
		}
	}

	private boolean addDisabledPlayer(Player player) {
		DisablePlayers.add(player.getUniqueId().toString());
		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			return true;
		}
		if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains((player).getWorld().getName())) {
			return true;
		}
		if (!FlightCommand.FlyingPlayers.contains(player.getUniqueId().toString())) {
			player.setAllowFlight(false);
			player.setFlying(false);
		}
		return true;
	}

	private boolean addEnabledPlayer(Player player) {
		FlightCommand.FlyingPlayers.remove(player.getUniqueId().toString());
		if (!DisablePlayers.contains(player.getUniqueId().toString())) {
			return true;
		}
		DisablePlayers.remove(player.getUniqueId().toString());
		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			return true;
		}
		if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains((player).getWorld().getName())) {
			return true;
		}
		player.setAllowFlight(false);
		player.setFlying(false);
		return true;
	}

	// Returns false if the player is not in the correct world or doesn't have permissions
	private boolean checkWorldAndPerm(Player player) {
		if (!player.hasPermission("tdj.command")) {
			TreysDoubleJump.adventure().player(player).sendMessage(ConfigManager.getConfigMessage(
					"NoPermission"));
			return true;
		}
		if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains((player).getWorld().getName())) {
			TreysDoubleJump.adventure().player(player).sendMessage(ConfigManager.getConfigMessage(
					"NotInWorld"));
			return true;
		}
		return false;
	}

}
