package me.treyruffy.treysdoublejump.NMS.v1_8_R2;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.treyruffy.treysdoublejump.Interfaces.ParticlesMain;
import net.minecraft.server.v1_8_R2.EnumParticle;
import net.minecraft.server.v1_8_R2.PacketPlayOutWorldParticles;

public class Particle_1_8_R2 implements ParticlesMain {

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
