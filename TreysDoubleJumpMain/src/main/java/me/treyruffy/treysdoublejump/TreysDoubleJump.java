package me.treyruffy.treysdoublejump;

import java.io.File;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.treyruffy.treysdoublejump.Events.DoubleJump;
import me.treyruffy.treysdoublejump.Events.DoubleJumpCommand;
import me.treyruffy.treysdoublejump.Events.FlightCommand;
import me.treyruffy.treysdoublejump.Events.GroundPoundCommand;
import me.treyruffy.treysdoublejump.Events.NoFallDamage;
import me.treyruffy.treysdoublejump.Updater.Updates;
import me.treyruffy.treysdoublejump.Util.ConfigManager;
import me.treyruffy.treysdoublejump.Util.PAPI;
import me.treyruffy.treysdoublejump.Util.UpdateManager;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 01/26/2020
 */

public class TreysDoubleJump extends JavaPlugin implements Listener {

	private static TreysDoubleJump instance;

	public static TreysDoubleJump getInstance() {
		return instance;
	}

	//
	public static File dataFolder;
	
	// Sets up everything
	@Override
	public void onEnable(){
		instance = this;
		new ConfigManager();
		ConfigManager.reloadConfig();
		dataFolder = getDataFolder();
		new UpdateManager().setup();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new Updates(), this);
		pm.registerEvents(new DoubleJump(), this);
		pm.registerEvents(new NoFallDamage(), this);
		
		getCommand("fly").setExecutor(new FlightCommand());
		getCommand("tdj").setExecutor(new DoubleJumpCommand());
		getCommand("djreload").setExecutor(new DoubleJumpCommand());
		getCommand("groundpound").setExecutor(new GroundPoundCommand());
		
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
	
	// Grabs the cooldown from config
	public static Integer getCooldown(Player p){
		return DoubleJump.getCooldown(p);
	}
	
}