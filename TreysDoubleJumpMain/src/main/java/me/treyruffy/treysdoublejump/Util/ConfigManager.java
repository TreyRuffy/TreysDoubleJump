package me.treyruffy.treysdoublejump.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import me.treyruffy.treysdoublejump.TreysDoubleJump;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class ConfigManager {

	// Accesses the main class
	private static final TreysDoubleJump plugin = TreysDoubleJump.getInstance();
	
	// Accesses the configuration
	public static FileConfiguration MainConfig;
	public static FileConfiguration Messages;
	
	// Accesses the configuration file
	public static File MainConfigFile;
	public static File MessagesFile;
	
	// Sets up the config
	public void setup(){
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		MainConfigFile = new File(plugin.getDataFolder(), "config.yml");
		MessagesFile = new File(plugin.getDataFolder(), "messages.yml");
		
		if (!MainConfigFile.exists()) {
			try {
				MainConfigFile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + MainConfigFile + ".", e);
			}
		}
		if (!MessagesFile.exists()) {
			try {
				MessagesFile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + MessagesFile + ".", e);
			}
		}
		
		MainConfig = YamlConfiguration.loadConfiguration(MainConfigFile);
		Messages = YamlConfiguration.loadConfiguration(MessagesFile);
	}
	
	// Gets the config
	public static FileConfiguration getConfig() {
		if (MainConfig == null) {
			reloadConfig();
		}
		return MainConfig; 
	}

	// Gets the config
	public static FileConfiguration getMessages() {
		if (Messages == null) {
			reloadMessages();
		}
		return Messages;
	}
	
	// Saves the config
	public static void saveConfig() {
		if (MainConfig == null) {
			throw new NullArgumentException("Cannot save a non-existent file!");
		}
		try {
			MainConfig.save(MainConfigFile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + MainConfigFile + ".", e);
		}
	}

	public static void saveMessages() {
		if (Messages == null) {
			throw new NullArgumentException("Cannot save a non-existent file!");
		}
		try {
			Messages.save(MessagesFile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + Messages + ".", e);
		}
	}
	
	// Reloads the config
	public static void reloadConfig() {
		MainConfigFile = new File(plugin.getDataFolder(), "config.yml");
		if (!MainConfigFile.exists()) {
			plugin.saveResource("config.yml", false);
		}
		MainConfig = YamlConfiguration.loadConfiguration(MainConfigFile);
		InputStream configData = plugin.getResource("config.yml");
		if (configData != null) {
			MainConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(configData)));
		}
	}

	// Reloads the config
	public static void reloadMessages() {
		MessagesFile = new File(plugin.getDataFolder(), "messages.yml");
		if (!MessagesFile.exists()) {
			plugin.saveResource("messages.yml", false);
		}
		Messages = YamlConfiguration.loadConfiguration(MessagesFile);
		InputStream messageData = plugin.getResource("messages.yml");
		if (messageData != null) {
			Messages.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(messageData)));
		}
	}
}