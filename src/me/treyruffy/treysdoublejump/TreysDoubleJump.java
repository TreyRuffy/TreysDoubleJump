package me.treyruffy.treysdoublejump;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;

public class TreysDoubleJump extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
		
	@EventHandler
	public void onEntityDamage(EntityDamageEvent d) {
		if (((d.getEntity() instanceof Player)) && (d.getCause() == EntityDamageEvent.DamageCause.FALL)) {
			d.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if ((player.getGameMode() != GameMode.CREATIVE && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR))) {
			if ((player.hasPermission("tdj.use"))) {
				if ((Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null)){
					if ((player.hasPermission("tdj.ncp"))){
						NCPExemptionManager.exemptPermanently(player, fr.neatmonster.nocheatplus.checks.CheckType.MOVING_SURVIVALFLY);
						player.setAllowFlight(true);	
					}else{
						player.setAllowFlight(false);
					}
				}else{
					player.setAllowFlight(true);
				}
			}else{
				player.setAllowFlight(false);
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if ((player.getGameMode() != GameMode.CREATIVE && player.hasPermission("tdj.use"))) {
			
		}
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode() != GameMode.CREATIVE && player.hasPermission("tdj.use")) {
			event.setCancelled(true);
			player.setAllowFlight(false);
			player.setFlying(false);
			player.setVelocity(player.getLocation().getDirection().multiply(1.6D).setY(1.0D));
			player.playSound(player.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 0.33F, 0.5F);
			ParticleEffect.EXPLOSION_NORMAL.display(0, 0, 0, 0, 1, player.getLocation(), 2);
		}
	}
}
