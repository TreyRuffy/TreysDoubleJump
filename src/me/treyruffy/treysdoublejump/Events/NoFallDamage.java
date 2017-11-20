package me.treyruffy.treysdoublejump.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.treyruffy.treysdoublejump.ConfigManager;

public class NoFallDamage implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void noFall(EntityDamageEvent e) {
		if (ConfigManager.getConfig().getBoolean("NoFall.Enabled")){
			if (e.getEntity() instanceof Player){
				Player p = (Player) e.getEntity();
				if (p.hasPermission("tdj.nofall")){
					if (!DoubleJumpCommand.DisablePlayers.contains(p.getUniqueId().toString())){
						if (ConfigManager.getConfig().getStringList("EnabledWorlds").contains(p.getWorld().getName())){
							if (e.getCause().equals(DamageCause.FALL)){
								e.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
}
