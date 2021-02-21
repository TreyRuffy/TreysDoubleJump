package me.treyruffy.treysdoublejump;

import me.treyruffy.treysdoublejump.Util.ConfigManager;
import me.treyruffy.treysdoublejump.nmsreference.ParticlesMain;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

/**
 * Created by TreyRuffy on 01/26/2020.
 */

public class Particle_1_16_R3 implements ParticlesMain {

	// Sets particles for 1.16.4+
	@Override
	public void sendParticle(Player p, String particle, Location loc, int amount, float r, float g, float b) {
		if (ConfigManager.getConfig().getBoolean("Particles.AllPlayers")) {
			if (particle.equals("REDSTONE")) {
				p.getWorld().spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0,
						new Particle.DustOptions(Color.fromRGB((int) r * 100, (int) g * 100, (int) b * 100), 1));
			} else {
				p.getWorld().spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, 1);
			}
		} else {
			if (particle.equals("REDSTONE")) {
				p.spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, new Particle.DustOptions(Color.fromRGB((int) r * 100, (int) g * 100, (int) b * 100), 1));
			} else {
				p.spawnParticle(Particle.valueOf(particle), loc, amount, 0, 0, 0, 1);
			}
		}
	}
}
