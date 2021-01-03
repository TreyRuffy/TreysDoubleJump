package me.treyruffy.treysdoublejump.v1_8_R2;

import me.treyruffy.treysdoublejump.nmsreference.ParticlesMain;
import net.minecraft.server.v1_8_R2.EnumParticle;
import net.minecraft.server.v1_8_R2.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class Particle_1_8_R2 implements ParticlesMain {

	// Sets particles using packets for 1.8.3
	@Override
	public void sendParticle(Player p, String particle, Location loc, int amount, float r, float g, float b) {
		int x = 0;
		while (x <= amount){
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.valueOf(particle), true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), r, g, b, 1, 0);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			x++;
		}
	}
}
