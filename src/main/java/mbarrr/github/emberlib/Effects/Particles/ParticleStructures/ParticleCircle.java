package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class ParticleCircle extends ParticleStructure {



    public ParticleCircle(Plugin instance, double radius, Location location, Particle.DustOptions dustOptions, int duration) {
        super(instance, radius, dustOptions, location, duration);
    }

    @Override
    public void setX(){
        point.add(Math.sqrt(radius * radius - point.getZ() * point.getZ()), 0, 0);
    }
}
