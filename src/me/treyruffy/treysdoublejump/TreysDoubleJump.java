//   This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package me.treyruffy.treysdoublejump;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class TreysDoubleJump
  extends JavaPlugin
  implements Listener
{
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    getConfig().options().copyDefaults(true);
    saveConfig();
    try
    {
      Metrics metrics = new Metrics(this);
      metrics.start();
    }
    catch (IOException localIOException) {}
  }
  
  List<String> EnabledWorlds = getConfig().getStringList("EnabledWorlds");
  
  @EventHandler
  public void onEntityDamage(EntityDamageEvent d)
  {
    if (((d.getEntity() instanceof Player)) && (d.getCause() == EntityDamageEvent.DamageCause.FALL)) {
      d.setCancelled(true);
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
