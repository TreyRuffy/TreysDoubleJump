package me.treyruffy.treysdoublejump.Interfaces;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ParticlesMain {

	public void sendParticle(Player p, String particle, Location loc, int amount, int r, int g, int b);
	
}
