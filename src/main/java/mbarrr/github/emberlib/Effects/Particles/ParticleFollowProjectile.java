package mbarrr.github.emberlib.Effects.Particles;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Projectile;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleFollowProjectile {

    public ParticleFollowProjectile(Plugin plugin, Projectile projectile, Color color, float size) {

        Particle particle = Particle.REDSTONE;

        Particle.DustOptions dustOptions = new Particle.DustOptions(color, size);

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                projectile.getWorld().spawnParticle(particle, projectile.getLocation(), 0, 0, 0, 0, dustOptions);
            }
        };

        runnable.runTaskTimer(plugin, 0, 1);

        BukkitRunnable cancel = new BukkitRunnable() {
            @Override
            public void run() {
                runnable.cancel();
            }
        };
        cancel.runTaskLater(plugin, 100L);
    }

    public ParticleFollowProjectile(Plugin plugin, Particle particle, Projectile projectile) {

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                projectile.getWorld().spawnParticle(particle, projectile.getLocation(), 0, 0, 0, 0);
            }
        };

        runnable.runTaskTimer(plugin, 0, 1);

        BukkitRunnable cancel = new BukkitRunnable() {
            @Override
            public void run() {
                runnable.cancel();
            }
        };
        cancel.runTaskLater(plugin, 100L);
    }
}
