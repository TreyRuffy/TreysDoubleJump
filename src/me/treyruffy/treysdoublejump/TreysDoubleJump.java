package me.treyruffy.treysdoublejump;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TreysDoubleJump
  extends JavaPlugin
  implements Listener
{
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    getConfig().options().copyDefaults(true);
    saveConfig();
    try {
        Metrics metrics = new Metrics(this);
        metrics.start();
    } catch (IOException e) {
        // Failed to submit the stats :-(
    }
  }
  
  List<String> EnabledWorlds = getConfig().getStringList("EnabledWorlds");
  
  @EventHandler
  public void onEntityDamage(EntityDamageEvent d)
  {
	 if ((d.getEntity() instanceof Player)){
		 Player player = (Player)d.getEntity();
		 if ((d.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && (player.hasPermission("tdj.nofall"))){
			 for (String w: this.EnabledWorlds){
				 World world = Bukkit.getWorld(w);
				 if(player.getWorld().equals(world)){
					 d.setCancelled(true);
				 }
			 }
		 }
	 }
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent event)
  {
    Player player = event.getPlayer();
    if ((player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR)) {
      if (this.EnabledWorlds.contains(player.getWorld().getName()))
      {
        if (player.hasPermission("tdj.use"))
        {
          if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null)
          {
            if (player.hasPermission("tdj.ncp"))
            {
              NCPExemptionManager.exemptPermanently(player, CheckType.MOVING_SURVIVALFLY);
              player.setAllowFlight(true);
            }
            else
            {
              player.setAllowFlight(false);
            }
          }
          else {
            player.setAllowFlight(true);
          }
        }
        else {
          player.setAllowFlight(false);
        }
      }
      else {
        player.setAllowFlight(false);
      }
    }
  }
  
  @EventHandler
  public void onPlayerToggleFlight(PlayerToggleFlightEvent event)
  {
    Player player = event.getPlayer();
    if ((player.getGameMode() != GameMode.CREATIVE) && (player.hasPermission("tdj.use")) && 
      (this.EnabledWorlds.contains(player.getWorld().getName())))
    {
      event.setCancelled(true);
      player.setAllowFlight(false);
      player.setFlying(false);
      player.setVelocity(player.getLocation().getDirection().multiply(getConfig().getDouble("Velocity")).setY(1.0D));
      if ((player.hasPermission("tdj.sound")) && (getConfig().getBoolean("Sounds.Enabled"))) {
        player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("Sounds.Type")), 1.0F, 0.0F);
      }
    }
  }
}
