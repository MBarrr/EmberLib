package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PortalAnimation {

    private BukkitRunnable cancellationRunnable;
    private BukkitRunnable runnable;
    private int period = 1;
    private final JavaPlugin instance;
    private final int duration;
    private final int numParticles;
    private final Location location;
    private final double offset;
    private Particle particle;

    public PortalAnimation(JavaPlugin instance, Location location, int duration, PortalDirection direction) {
        this.period = 40;
        this.instance = instance;
        this.numParticles = 100;
        this.location = location;
        this.offset = 0;
        this.duration = duration;

        switch(direction){
            case ENTER:
                this.particle = Particle.PORTAL;
                break;

            case EXIT:
                this.particle = Particle.REVERSE_PORTAL;
                break;
        }

        start();
    }

    protected void onTick(){
        for(int i = 0; i < numParticles; i++){
            location.getWorld().spawnParticle(particle, location, 1, offset, 1, offset);
        }
    }

    public void start(){
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                onTick();
            }
        };

        runnable.runTaskTimer(instance, 0, period);


        cancellationRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                runnable.cancel();
            }
        };
        cancellationRunnable.runTaskLater(instance, duration);

    }

    public void stop(){
        cancellationRunnable.cancel();
        runnable.cancel();
    }
}
