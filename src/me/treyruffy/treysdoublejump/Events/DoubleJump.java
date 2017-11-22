package me.treyruffy.treysdoublejump.Events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import me.treyruffy.treysdoublejump.ConfigManager;
import me.treyruffy.treysdoublejump.TreysDoubleJump;
import me.treyruffy.treysdoublejump.Interfaces.ParticlesMain;
import me.treyruffy.treysdoublejump.NMS.v1_10_R1.Particle_1_10_R1;
import me.treyruffy.treysdoublejump.NMS.v1_11_R1.Particle_1_11_R1;
import me.treyruffy.treysdoublejump.NMS.v1_12_R1.Particle_1_12_R1;
import me.treyruffy.treysdoublejump.NMS.v1_8_R1.Particle_1_8_R1;
import me.treyruffy.treysdoublejump.NMS.v1_8_R2.Particle_1_8_R2;
import me.treyruffy.treysdoublejump.NMS.v1_8_R3.Particle_1_8_R3;
import me.treyruffy.treysdoublejump.NMS.v1_9_R1.Particle_1_9_R1;
import me.treyruffy.treysdoublejump.NMS.v1_9_R2.Particle_1_9_R2;

public class DoubleJump implements Listener {

	private HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	private HashMap<Player, BukkitRunnable> cooldownTask= new HashMap<Player, BukkitRunnable>();
	ArrayList<String> NCPPlayer = new ArrayList<String>();
	public static ArrayList<String> Grounded = new ArrayList<String>();
	
	public Integer getCooldown(Player p){
		return cooldown.get(p);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (NCPPlayer.contains(p.getUniqueId().toString())) {
			try {
				NCPExemptionManager.unexempt(p, CheckType.MOVING_SURVIVALFLY);
				NCPPlayer.remove(p.getUniqueId().toString());
			} catch (Exception ex){}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (p.isFlying()) {
			return;
		}
		if (!p.hasPermission("tdj.use")) {
			return;
		}
		if (p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (p.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains(p.getWorld().getName())){
			return;
		}
		if (!p.isOnGround()) {
			return;
		}
		if (cooldown.containsKey(p)) {
			return;
		}
		if (DoubleJumpCommand.DisablePlayers.contains(p.getUniqueId().toString())) {
			return;
		}
		if (FlightCommand.FlyingPlayers.contains(p.getUniqueId().toString())) {
			return;
		}
		
		if (Grounded.contains(p.getUniqueId().toString())) {
			Grounded.remove(p.getUniqueId().toString());
		}
		
		if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null) {
			if (p.hasPermission("tdj.ncp")) {
				if (NCPExemptionManager.isExempted(p, CheckType.MOVING_SURVIVALFLY)) {
					p.setAllowFlight(true);
					return;
				}
				NCPExemptionManager.exemptPermanently(p, CheckType.MOVING_SURVIVALFLY);
				p.setAllowFlight(true);
				NCPPlayer.add(p.getUniqueId().toString());
				Bukkit.getScheduler().scheduleSyncDelayedTask(TreysDoubleJump.getPlugin(TreysDoubleJump.class), new Runnable() {
					
					@Override
					public void run() {
						try {
							NCPExemptionManager.unexempt(p, CheckType.MOVING_SURVIVALFLY);
							NCPPlayer.remove(p.getUniqueId().toString());
						} catch (Exception ex){}
					}
				}, 60L);
				return;
			}
			return;
		}
		
		p.setAllowFlight(true);
	}
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
		final Player p = e.getPlayer();
		if (FlightCommand.FlyingPlayers.contains(p.getUniqueId().toString())) {
			return;
		}
		if (cooldown.containsKey(p)) {
			return;
		}
		if (p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (p.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		if (!p.hasPermission("tdj.use")) {
			return;
		}
		if (!ConfigManager.getConfig().getStringList("EnabledWorlds").contains(p.getWorld().getName())){
			return;
		}
		if (DoubleJumpCommand.DisablePlayers.contains(p.getUniqueId().toString())) {
			return;
		}
		
		Grounded.add(p.getUniqueId().toString());
		
		e.setCancelled(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		
		
		if (ConfigManager.getConfig().getBoolean("Cooldown.Enabled")){
			cooldown.put(p, ConfigManager.getConfig().getInt("Cooldown.Time"));
			cooldownTask.put(p, new BukkitRunnable() {
				@Override
				public void run() {
					cooldown.put(p, cooldown.get(p) - 1);
					if (cooldown.get(p) == 0){
						cooldown.remove(p);
						cooldownTask.remove(p);
						cancel();
					}
				}
			});
			cooldownTask.get(p).runTaskTimer(TreysDoubleJump.getPlugin(TreysDoubleJump.class), 20, 20);
		}
		
		p.setVelocity(p.getLocation().getDirection().multiply(ConfigManager.getConfig().getDouble("Velocity.Forward")).setY(ConfigManager.getConfig().getDouble("Velocity.Up")));
		
		if ((p.hasPermission("tdj.sounds")) && (ConfigManager.getConfig().getBoolean("Sounds.Enabled"))) {
			p.playSound(p.getLocation(), Sound.valueOf(ConfigManager.getConfig().getString("Sounds.Type")), ConfigManager.getConfig().getInt("Sounds.Volume"), ConfigManager.getConfig().getInt("Sounds.Pitch"));
		} 
		
    	if ((p.hasPermission("tdj.particles")) && (ConfigManager.getConfig().getBoolean("Particles.Enabled"))){
    		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    		ParticlesMain particles = null;
    		if (version.equals("v1_8_R1")){
    			particles = new Particle_1_8_R1();
    		} else if (version.equals("v1_8_R2")){
    			particles = new Particle_1_8_R2();
    		} else if (version.equals("v1_8_R3")){
    			particles = new Particle_1_8_R3();
    		} else if (version.equals("v1_9_R1")){
    			particles = new Particle_1_9_R1();
    		} else if (version.equals("v1_9_R2")){
    			particles = new Particle_1_9_R2();
    		} else if (version.equals("v1_10_R1")){
    			particles = new Particle_1_10_R1();
    		} else if (version.equals("v1_11_R1")){
    			particles = new Particle_1_11_R1();
    		} else if (version.equals("v1_12_R1")){
    			particles = new Particle_1_12_R1();
    		}
    		if (ConfigManager.getConfig().getBoolean("Particles.AllPlayers")){
				for (Player players : Bukkit.getOnlinePlayers()){
					particles.sendParticle(players, ConfigManager.getConfig().getString("Particles.Type"), p.getLocation(), ConfigManager.getConfig().getInt("Particles.Amount"), ConfigManager.getConfig().getInt("Particles.R"), ConfigManager.getConfig().getInt("Particles.G"), ConfigManager.getConfig().getInt("Particles.B"));
				}
			} else {
				particles.sendParticle(p, ConfigManager.getConfig().getString("Particles.Type"), p.getLocation(), ConfigManager.getConfig().getInt("Particles.Amount"), ConfigManager.getConfig().getInt("Particles.R"), ConfigManager.getConfig().getInt("Particles.G"), ConfigManager.getConfig().getInt("Particles.B"));
			}
    	}
    	p.setFallDistance(-256);
		return;
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (p.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		if (!p.hasPermission("tdj.use")) {
			return;
		}
		if (!p.hasPermission("tdj.groundpound")) {
			return;
		}
		if (!Grounded.contains(p.getUniqueId().toString())) {
			return;
		}
		if (p.isOnGround()) {
			return;
		}
		if (FlightCommand.FlyingPlayers.contains(p.getUniqueId().toString())) {
			return;
		}
		if (DoubleJumpCommand.DisablePlayers.contains(p.getUniqueId().toString())) {
			return;
		}
		if (!ConfigManager.getConfig().getBoolean("GroundPound.Enabled")){
			return;
		}
		p.setVelocity(new Vector(0, -5, 0));
	}
	
}
