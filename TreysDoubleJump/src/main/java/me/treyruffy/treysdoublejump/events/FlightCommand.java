package me.treyruffy.treysdoublejump.events;

import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.util.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class FlightCommand implements CommandExecutor {

	// Players in this list can fly
	public static List<String> FlyingPlayers = new ArrayList<>();
	
	// Sets all commands for /fly
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("fly")) {
			return true;
		}
		if (!ConfigManager.getConfig().getBoolean("Flight.Enabled")){
			TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
					"FlyCommandDisabled"));
			return true;
		}

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("enable")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (p.hasPermission("tdj.fly")) {
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage("FlyToggledOn"));
						addEnabledFlightPlayer(p);
					} else {
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage("NoPermission"));
					}
				}
			} else if (args[0].equalsIgnoreCase("disable")) {
				Player p = (Player) sender;
				if (p.hasPermission("tdj.fly")) {
					TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage("FlyToggledOff"));
					addDisabledFlightPlayer(p);
				} else {
					TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage("NoPermission"));
				}
  			} else if (Bukkit.getPlayer(args[0]) != null) {
				if (!sender.hasPermission("tdj.fly.toggleothers")) {
					TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
							"NoPermission"));
					return true;
				}

				Player username = Bukkit.getPlayer(args[0]);
				assert username != null;

				if (args.length > 1) {

					if (args[1].equalsIgnoreCase("enable")) {
						turnFlyOn(sender, username);
					} else if (args[1].equalsIgnoreCase("disable")) {
						TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
								"FlyToggledOffOther").replaceText("[user]", Component.text(username.getName())));
						addDisabledFlightPlayer(username);
						if (!LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
								"FlightToggledOff")).equalsIgnoreCase(""))
							TreysDoubleJump.adventure().player(username).sendMessage(ConfigManager.getConfigMessage(
									"FlightToggledOff"));
					} else {
						if (sender instanceof Player) {
							TreysDoubleJump.adventure().player((Player) sender).sendMessage(ConfigManager.getConfigMessage(
									"InvalidFlyArgumentWithOther"));
						} else {
							TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage("InvalidFlyArgumentConsole"));
						}
					}
					return true;
				}

				if (FlyingPlayers.contains(username.getUniqueId().toString())) {
					TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
							"FlyToggledOffOther").replaceText("[user]", Component.text(username.getName())));
					addDisabledFlightPlayer(username);

					if (!LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
							"FlightToggledOff")).equalsIgnoreCase("")) {
						System.out.println(LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
								"FlightToggledOff")));
						TreysDoubleJump.adventure().player(username).sendMessage(ConfigManager.getConfigMessage(
								"FlightToggledOff"));
						}
				} else {
					turnFlyOn(sender, username);
				}
			} else {
				if (sender.hasPermission("tdj.fly.toggleothers")) {
					TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
							"PlayerNotFound").replaceText("[user]", Component.text(args[0])));
				} else if (sender.hasPermission("tdj.fly")) {
					TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage("InvalidFlyArgument"));
				} else {
					TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage("NoPermission"));
				}
				return true;
			}

		}

		// /fly
		else {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("tdj.fly")) {
					if (!checkIfInWorld(p)) {
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
								"NotInWorld"));
						return true;
					}
					if (FlyingPlayers.contains(p.getUniqueId().toString())) {
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
								"FlyToggledOff"));
						addDisabledFlightPlayer(p);
					} else {
						TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
								"FlyToggledOn"));
						addEnabledFlightPlayer(p);
					}
				} else {
					TreysDoubleJump.adventure().player(p).sendMessage(ConfigManager.getConfigMessage(
							"NoPermission"));
				}
				return true;
			}
			TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
					"PlayersOnly"));
		}
		return true;
	}

	private void turnFlyOn(@NotNull CommandSender sender, Player username) {
		TreysDoubleJump.adventure().sender(sender).sendMessage(ConfigManager.getConfigMessage(
				"FlyToggledOnOther").replaceText("[user]", Component.text(username.getName())));
		addEnabledFlightPlayer(username);
		if (!LegacyComponentSerializer.legacy(ChatColor.COLOR_CHAR).serialize(ConfigManager.getConfigMessage(
				"FlightToggledOn")).equalsIgnoreCase(""))
			TreysDoubleJump.adventure().player(username).sendMessage(ConfigManager.getConfigMessage(
					"FlightToggledOn"));
	}

	private boolean checkIfInWorld(Player player) {
		return ConfigManager.getConfig().getStringList("EnabledWorlds").contains((player).getWorld().getName());
	}

	private void addDisabledFlightPlayer(Player player) {
		player.setFallDistance(0f);
		player.setAllowFlight(false);
		player.setFlying(false);
		FlyingPlayers.remove(player.getUniqueId().toString());
	}

	private void addEnabledFlightPlayer(Player player) {
		player.setFallDistance(0f);
		DoubleJump.Grounded.remove(player.getUniqueId().toString());
		player.setAllowFlight(true);
		player.setFlying(true);
		FlyingPlayers.add(player.getUniqueId().toString());
	}
	
}
