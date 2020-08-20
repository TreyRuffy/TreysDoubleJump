package me.treyruffy.treysdoublejump.nmsreference;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public interface ParticlesMain {

	// Interface for all versions 1.8 - 1.15
	void sendParticle(Player p, String particle, Location loc, int amount, int r, int g, int b);
	
}
