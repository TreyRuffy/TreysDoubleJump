package me.treyruffy.treysdoublejump;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import me.treyruffy.treysdoublejump.Interfaces.ParticlesMain;
import me.treyruffy.treysdoublejump.NMS.v1_10_R1.Particle_1_10_R1;
import me.treyruffy.treysdoublejump.NMS.v1_11_R1.Particle_1_11_R1;
import me.treyruffy.treysdoublejump.NMS.v1_12_R1.Particle_1_12_R1;
import me.treyruffy.treysdoublejump.NMS.v1_7_R1.Particle_1_7_R1;
import me.treyruffy.treysdoublejump.NMS.v1_7_R2.Particle_1_7_R2;
import me.treyruffy.treysdoublejump.NMS.v1_7_R3.Particle_1_7_R3;
import me.treyruffy.treysdoublejump.NMS.v1_7_R4.Particle_1_7_R4;
import me.treyruffy.treysdoublejump.NMS.v1_8_R1.Particle_1_8_R1;
import me.treyruffy.treysdoublejump.NMS.v1_8_R2.Particle_1_8_R2;
import me.treyruffy.treysdoublejump.NMS.v1_8_R3.Particle_1_8_R3;
import me.treyruffy.treysdoublejump.NMS.v1_9_R1.Particle_1_9_R1;
import me.treyruffy.treysdoublejump.NMS.v1_9_R2.Particle_1_9_R2;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TreysDoubleJump extends JavaPlugin implements Listener {
	List<String> EnabledWorlds = getConfig().getStringList("EnabledWorlds");
	List<String> DisabledPlayers = new ArrayList<String>();
	List<String> Flying = new ArrayList<String>();
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		if ((version.equals("v1_7_R1")||version.equals("v1_7_R2")||version.equals("v1_7_R3")||version.equals("v1_7_R4")||version.equals("v1_8_R1")||version.equals("v1_8_R2")||version.equals("v1_8_R3"))){
			String s = getConfig().getString("Sounds.Type");
			if (s.contains("BLOCK_")||s.contains("ENTITY_")){
				String t = s.replace("BLOCK_", "").replace("ENTITY_", "");
				getConfig().set("Sounds.Type", t);
				saveConfig();
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			if (EnabledWorlds.contains(((Player) sender).getWorld().getName())){
				if (cmd.getName().equalsIgnoreCase("tdj")){
					if (sender.hasPermission("tdj.command")){
						if (DisabledPlayers.contains(((Player) sender).getUniqueId().toString())){
							DisabledPlayers.remove(((Player) sender).getUniqueId().toString());
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.ToggledOn")));
							return true;
						} else {
							DisabledPlayers.add(((Player) sender).getUniqueId().toString());
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.ToggledOff")));
							((Player) sender).setFlying(false);
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.NoPermission")));
						return false;
					}
				} else if (cmd.getName().equalsIgnoreCase("fly")){
					if (getConfig().getBoolean("Flight.Enabled")){
						if (sender instanceof Player){
							if (sender.hasPermission("tdj.fly")){
								if (Flying.contains(((Player) sender).getUniqueId().toString())){
									Flying.remove(((Player) sender).getUniqueId().toString());
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.FlyToggledOff")));
									return true;
								} else {
									Flying.add(((Player) sender).getUniqueId().toString());
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.FlyToggledOn")));
									((Player) sender).setFlying(false);
									return true;
								}
							} else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.NoPermission")));
								return false;
							}
						}
					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.FlyCommandDisabled")));
						return false;
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.PlayersOnly")));
			return false;
		}
		return false;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void noFall(EntityDamageEvent e){
		if (e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if (p.hasPermission("tdj.nofall")){
				if (!DisabledPlayers.contains(p.getUniqueId().toString())){
					if (EnabledWorlds.contains(p.getWorld().getName())){
						if (e.getCause().equals(DamageCause.FALL)){
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
  	public void onMove(PlayerMoveEvent event){
		final Player player = event.getPlayer();
		if (!Flying.contains(player.getUniqueId().toString())){
			if (!DisabledPlayers.contains(player.getUniqueId().toString())){
				if ((player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR)) {
					  if (EnabledWorlds.contains(player.getWorld().getName())){
						  if (player.hasPermission("tdj.use")){
							  if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null) {
								  if (player.hasPermission("tdj.ncp")) {
									  NCPExemptionManager.exemptPermanently(player, CheckType.MOVING_SURVIVALFLY);
									  player.setAllowFlight(true);
									  Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
										
										@Override
										public void run() {
											NCPExemptionManager.unexempt(player, CheckType.MOVING_SURVIVALFLY);;
										}
									}, 60L);
								  } else {
									  player.setAllowFlight(false);
								  }
							  } else {
								  player.setAllowFlight(true);
							  }
						  } else {
							  player.setAllowFlight(false);
						  }
					  } else {
						  player.setAllowFlight(false);
					  }
				  }
			} else {
				player.setAllowFlight(false);
			}
		} else {
			player.setAllowFlight(true);
			if (Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null) {
				NCPExemptionManager.exemptPermanently(player, CheckType.MOVING_SURVIVALFLY);
			}
		}
	}
  
  
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (!Flying.contains(player.getUniqueId().toString())){
			if (!DisabledPlayers.contains(player.getUniqueId().toString())){
				if ((player.getGameMode() != GameMode.CREATIVE) && ((player.hasPermission("tdj.use")) && (this.EnabledWorlds.contains(player.getWorld().getName())))){
					event.setCancelled(true);
					player.setAllowFlight(false);
					player.setFlying(false);
					player.setVelocity(player.getLocation().getDirection().multiply(getConfig().getDouble("Velocity.Forward")).setY(getConfig().getDouble("Velocity.Up")));
					if ((player.hasPermission("tdj.sounds")) && (getConfig().getBoolean("Sounds.Enabled"))) {
						player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("Sounds.Type")), getConfig().getInt("Sounds.Volume"), getConfig().getInt("Sounds.Pitch"));
					} 
			    	if ((player.hasPermission("tdj.particles")) && (getConfig().getBoolean("Particles.Enabled"))){
			    		ParticlesMain particles = null;
			    		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
			    		if (version.equals("v1_7_R1")){
			    			particles = new Particle_1_7_R1();
			    		} else if (version.equals("v1_7_R2")){
			    			particles = new Particle_1_7_R2();
			    		} else if (version.equals("v1_7_R3")){
			    			particles = new Particle_1_7_R3();
			    		} else if (version.equals("v1_7_R4")){
			    			particles = new Particle_1_7_R4();
			    		} else if (version.equals("v1_8_R1")){
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
			    		if (getConfig().getBoolean("Particles.AllPlayers")){
							for (Player players : Bukkit.getOnlinePlayers()){
								particles.sendParticle(players, getConfig().getString("Particles.Type"), player.getLocation(), getConfig().getInt("Particles.Amount"), getConfig().getInt("Particles.R"), getConfig().getInt("Particles.G"), getConfig().getInt("Particles.B"));
							}
						} else {
							particles.sendParticle(player, getConfig().getString("Particles.Type"), player.getLocation(), getConfig().getInt("Particles.Amount"), getConfig().getInt("Particles.R"), getConfig().getInt("Particles.G"), getConfig().getInt("Particles.B"));
						}
			    		player.setFallDistance(-256);
			      }
				}
			}
		}
	}
}