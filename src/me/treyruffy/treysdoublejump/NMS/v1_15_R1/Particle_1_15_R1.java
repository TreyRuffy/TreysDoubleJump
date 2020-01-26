package me.treyruffy.treysdoublejump.NMS.v1_15_R1;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Interfaces.ParticlesMain;
import me.treyruffy.treysdoublejump.Util.ConfigManager;

/**
 * Created by TreyRuffy on 01/26/2020.
 */

public class Particle_1_15_R1 implements ParticlesMain {

	// Sets particles using packets for 1.15
	@Override
	public void sendParticle(Player p, String particle, Location loc, int amount, int r, int g, int b) {
		if (ConfigManager.getConfig().getBoolean("Particles.AllPlayers")) {
			if (particle.equals("REDSTONE")) {
				p.getWorld().spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(r * 100, g * 100, b * 100), 1));
			} else {
				p.getWorld().spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, 1);
			}
		} else {
			if (particle.equals("REDSTONE")) {
				p.spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(r * 100, g * 100, b * 100), 1));
			} else {
				p.spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, 1);
			}
		}
	}
}
