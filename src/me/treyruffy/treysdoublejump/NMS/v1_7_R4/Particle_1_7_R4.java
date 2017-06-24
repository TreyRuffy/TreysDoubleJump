package me.treyruffy.treysdoublejump.NMS.v1_7_R4;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Interfaces.ParticlesMain;
import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;

public class Particle_1_7_R4 implements ParticlesMain {

	@Override
	public void sendParticle(Player p, String particle, Location loc, int amount, int r, int g, int b) {
		int x = 0;
		while (x <= amount){
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), r, g, b, 1, 0);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}

}
