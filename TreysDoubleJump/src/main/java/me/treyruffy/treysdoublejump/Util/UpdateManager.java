package me.treyruffy.treysdoublejump.Util;

import me.treyruffy.treysdoublejump.TreysDoubleJump;

import java.util.Objects;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class UpdateManager {
	
	// Updates the config
	public void setup() {
		if (ConfigManager.getConfig().getString("Version") == null) {
			ConfigManager.getConfig().set("Version", TreysDoubleJump.getInstance().getDescription().getVersion());
			ConfigManager.saveConfig();
			return;
		}

		int lastVersion = Integer.parseInt(Objects.requireNonNull(ConfigManager.getConfig().getString("Version")).replace(".", ""));

		if (lastVersion < 263) {
			ConfigManager.getConfig().set("Messages.PlayerNotFound", "&c[user] is not online.");
			ConfigManager.getConfig().set("Messages.ToggledOnOther", "&6You have &a&lenabled &6double jump for " +
					"[user]!");
			ConfigManager.getConfig().set("Messages.ToggledOffOther", "&6You have &c&ldisabled &6double jump for " +
					"[user]!");
			ConfigManager.getConfig().set("Messages.InvalidArgument", "&cInvalid argument: Please do /tdj " +
					"[enable/disable]");
			ConfigManager.getConfig().set("Messages.InvalidArgumentConsole", "&cInvalid argument: Please do /tdj " +
					"<username> [enable/disable]");
			ConfigManager.getConfig().set("Messages.InvalidArgumentWithOther", "&cInvalid argument: Please do " +
					"/tdj [username] [enable/disable]");
			ConfigManager.getConfig().set("Messages.InvalidFlyArgument", "&cInvalid argument: Please do /fly " +
					"[enable/disable]");
			ConfigManager.getConfig().set("Messages.InvalidFlyArgumentConsole", "&cInvalid argument: Please do /fly " +
					"<username> [enable/disable]");
			ConfigManager.getConfig().set("Messages.InvalidFlyArgumentWithOther", "&cInvalid argument: Please do " +
					"/fly [username] [enable/disable]");
			ConfigManager.getConfig().set("Messages.FlyToggledOnOther", "&6You have &a&lenabled &6flight for [user]!");
			ConfigManager.getConfig().set("Messages.FlyToggledOnOther", "&6You have &c&ldisabled &6flight for [user]!");
			ConfigManager.getConfig().set("Messages.FlightToggledOn", "&6Your flight has been &a&lenabled&6!");
			ConfigManager.getConfig().set("Messages.FlightToggledOn", "&6Your flight has been &c&ldisabled&6!");
			ConfigManager.getConfig().set("Messages.DoubleJumpToggledOn", "&6Your double jump has been &a&lenabled&6!");
			ConfigManager.getConfig().set("Messages.DoubleJumpToggledOff", "&6Your double jump has been " +
					"&c&ldisabled&6!");
		}

		ConfigManager.getConfig().set("Version", TreysDoubleJump.getInstance().getDescription().getVersion());
		ConfigManager.saveConfig();
	}
	
}
