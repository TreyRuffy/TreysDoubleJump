package me.treyruffy.treysdoublejump;

import me.treyruffy.treysdoublejump.nmsreference.ParticlesMain;
import me.treyruffy.treysdoublejump.Util.ConfigManager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

/**
 * Created by TreyRuffy on 01/26/2020.
 */

public class Particle_1_16_R1 implements ParticlesMain {

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
