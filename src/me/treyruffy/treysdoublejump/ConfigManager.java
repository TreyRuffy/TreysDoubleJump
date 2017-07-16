package me.treyruffy.treysdoublejump;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	private static TreysDoubleJump plugin = TreysDoubleJump.getPlugin(TreysDoubleJump.class);
	
	public static FileConfiguration MainConfig;
	public static File MainConfigFile;
	
	public void setup(){
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		
		MainConfigFile = new File(plugin.getDataFolder(), "config.yml");
		
		if(!MainConfigFile.exists()){
			try{
				MainConfigFile.createNewFile();
			}catch(IOException e){
				Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + MainConfigFile + ".", e);
			}
		}
		
		MainConfig = YamlConfiguration.loadConfiguration(MainConfigFile);
	}
	
	public static FileConfiguration getConfig(){
		if (MainConfig == null){
			reloadConfig();
		}
		return MainConfig; 
	}
	
	public static void saveConfig(){
		if (MainConfig == null){
			throw new NullArgumentException("Cannot save a non-existant file!");
		}
		try{
			MainConfig.save(MainConfigFile);
		}catch(IOException e){
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save " + MainConfigFile + ".", e);
		}
	}
	
	public static void reloadConfig(){
		MainConfigFile = new File(plugin.getDataFolder(), "config.yml");
		if (!MainConfigFile.exists()){
			plugin.saveResource("config.yml", false);
		}
		MainConfig = YamlConfiguration.loadConfiguration(MainConfigFile);
		InputStream configData = plugin.getResource("config.yml");
		if (configData != null){
			MainConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(configData)));
		}
	}
}
