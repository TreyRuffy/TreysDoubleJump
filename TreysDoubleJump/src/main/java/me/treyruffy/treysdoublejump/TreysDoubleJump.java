package me.treyruffy.treysdoublejump;

import me.treyruffy.treysdoublejump.events.*;
import me.treyruffy.treysdoublejump.updater.Updates;
import me.treyruffy.treysdoublejump.util.ConfigManager;
import me.treyruffy.treysdoublejump.util.PAPI;
import me.treyruffy.treysdoublejump.util.UpdateManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 01/03/2021
 */

public class TreysDoubleJump extends JavaPlugin implements Listener {

	private static TreysDoubleJump instance;

	private static BukkitAudiences adventure;

	public static TreysDoubleJump getInstance() {
		return instance;
	}

	public static File dataFolder;

	private static boolean canUseHex = false;

	// Sets up everything
	@Override
	public void onEnable() {
		instance = this;
		adventure = BukkitAudiences.create(this);
		ConfigManager.reloadConfig();
		dataFolder = getDataFolder();
		new UpdateManager().setup();
		setUseHexCode();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new Updates(), this);
		pm.registerEvents(new DoubleJump(), this);
		pm.registerEvents(new NoFallDamage(), this);
		pm.registerEvents(new PlayerWorldSwitchEvent(), this);

		Objects.requireNonNull(getCommand("fly")).setExecutor(new FlightCommand());
		Objects.requireNonNull(getCommand("tdj")).setExecutor(new DoubleJumpCommand());
		Objects.requireNonNull(getCommand("djreload")).setExecutor(new DoubleJumpCommand());
		Objects.requireNonNull(getCommand("groundpound")).setExecutor(new GroundPoundCommand());

		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
			new PAPI(this).register();
		}
		if (ConfigManager.getConfig().getBoolean("Metrics.Enabled")) {
			new Metrics(this, 1848);
		}

		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		if ((version.equals("v1_8_R1")||version.equals("v1_8_R2")||version.equals("v1_8_R3"))){
			String s = ConfigManager.getConfig().getString("Sounds.Type");
			assert s != null;
			if (s.contains("BLOCK_")||s.contains("ENTITY_")){
				String t = s.replace("BLOCK_", "").replace("ENTITY_", "");
				ConfigManager.getConfig().set("Sounds.Type", t);
				ConfigManager.saveConfig();
			}
		}
		Updates.updateCheck();
	}

	public static boolean canUseHexColorCode() {
		return canUseHex;
	}

	private static void setUseHexCode() {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		boolean useHex = false;
		switch (version) {
			case "v1_8_R1":
			case "v1_8_R2":
			case "v1_8_R3":
			case "v1_9_R1":
			case "v1_9_R2":
			case "v1_10_R1":
			case "v1_11_R1":
			case "v1_12_R1":
			case "v1_13_R1":
			case "v1_13_R2":
			case "v1_14_R1":
			case "v1_15_R1":
				break;
			case "v1_16_R1":
			case "v1_16_R2":
			case "v1_16_R3":
				useHex = true;
				break;
			default:
				if (version.substring(3).startsWith("1")) {
					useHex = true;
				}
				break;
			}
		canUseHex = useHex;
		}

	public static @NotNull BukkitAudiences adventure() {
		if (adventure == null) {
			throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
		}
		return adventure;
	}
}