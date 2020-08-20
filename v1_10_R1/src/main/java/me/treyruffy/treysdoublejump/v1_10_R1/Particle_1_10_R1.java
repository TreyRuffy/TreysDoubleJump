package me.treyruffy.treysdoublejump.v1_10_R1;

import me.treyruffy.treysdoublejump.nmsreference.ParticlesMain;
import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class Particle_1_10_R1 implements ParticlesMain {

	// Sets particles using packets for 1.10
	@Override
	public void sendParticle(Player p, String particle, Location loc, int amount, int r, int g, int b) {
		int x = 0;
		while (x <= amount){
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.valueOf(particle), true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), (float) r, (float) g, (float) b, 1, 0);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			x++;
		}
	}

}
