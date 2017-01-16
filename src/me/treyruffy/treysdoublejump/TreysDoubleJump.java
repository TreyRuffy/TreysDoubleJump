package me.treyruffy.treysdoublejump;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import me.treyruffy.treysdoublejump.particleeffect.ParticleEffect;
import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TreysDoubleJump
  extends JavaPlugin
  implements Listener
{
	  List<String> EnabledWorlds = getConfig().getStringList("EnabledWorlds");
	
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    getConfig().options().copyDefaults(true);
    saveConfig();
    if(getConfig().getBoolean("UseMetrics")){
    	try {
    		Metrics metrics = new Metrics(this);
    		metrics.start();
    	} catch (IOException e) {
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
          } else {
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
    if ((player.getGameMode() != GameMode.CREATIVE) && ((player.hasPermission("tdj.use")) && 
      (this.EnabledWorlds.contains(player.getWorld().getName()))))
    {
      event.setCancelled(true);
      player.setAllowFlight(false);
      player.setFlying(false);
      player.setVelocity(player.getLocation().getDirection().multiply(getConfig().getDouble("Velocity.Forward")).setY(getConfig().getDouble("Velocity.Up")));
      if ((player.hasPermission("tdj.sounds")) && (getConfig().getBoolean("Sounds.Enabled"))) {
    		  player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("Sounds.Type")), 1.0F, 0.0F);
      	}if ((player.hasPermission("tdj.particles")) && (getConfig().getBoolean("Particles.Enabled"))){
    	  	if(getConfig().getBoolean("LowerVersions")){
    		  ParticleEffect.valueOf(getConfig().getString("Particles.Type")).display(0, -1, 0, 0, getConfig().getInt("Particles.Amount"), player.getLocation(), 2);
    	  	} else {
    		  	player.spawnParticle(Particle.valueOf(getConfig().getString("Particles.Type")), player.getLocation().add(0, -1, 0), getConfig().getInt("Particles.Amount"));
    	  	}
    	  }
      }
  }
}