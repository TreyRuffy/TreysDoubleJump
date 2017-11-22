package me.treyruffy.treysdoublejump;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.treyruffy.treysdoublejump.Events.DoubleJump;
import me.treyruffy.treysdoublejump.Events.DoubleJumpCommand;
import me.treyruffy.treysdoublejump.Events.FlightCommand;
import me.treyruffy.treysdoublejump.Events.NoFallDamage;
import me.treyruffy.treysdoublejump.Updater.Updates;

public class TreysDoubleJump extends JavaPlugin implements Listener {
	
	
	@Override
	public void onEnable(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new Updates(), this);
		pm.registerEvents(new DoubleJump(), this);
		pm.registerEvents(new NoFallDamage(), this);
		
		getCommand("fly").setExecutor(new FlightCommand());
		getCommand("tdj").setExecutor(new DoubleJumpCommand());
		getCommand("djreload").setExecutor(new DoubleJumpCommand());
		
		new ConfigManager();
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
			new PAPI(this).hook();
		}
		ConfigManager.reloadConfig();
		new Metrics(this);
		
		
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		if ((version.equals("v1_8_R1")||version.equals("v1_8_R2")||version.equals("v1_8_R3"))){
			String s = ConfigManager.getConfig().getString("Sounds.Type");
			if (s.contains("BLOCK_")||s.contains("ENTITY_")){
				String t = s.replace("BLOCK_", "").replace("ENTITY_", "");
				ConfigManager.getConfig().set("Sounds.Type", t);
				ConfigManager.saveConfig();
			}
		}
		Updates.updateCheck();
	}  
	
	public Integer getCooldown(Player p){
		return new DoubleJump().getCooldown(p);
	}
	
}