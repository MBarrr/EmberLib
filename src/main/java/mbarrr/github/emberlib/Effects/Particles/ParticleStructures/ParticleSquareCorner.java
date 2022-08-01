package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class ParticleSquareCorner extends ParticleStructure {


    Location point2;
    private Color endColor;

    public ParticleSquareCorner(Plugin instance, double radius, Location location, int duration, Particle.DustOptions dustOptions) {
        super(instance, radius, dustOptions, location, duration);



        setPoint(getPoint().add(-getRadius(), 0, 0));
        point2 = getPoint().clone();
    }



    @Override
    public void onTick(){
        if (getPoint().getZ() < -getRadius() || getPoint().getZ() > getRadius()) {
            xIncrement *= -1;
        }

        getPoint().add(0, 0, xIncrement);
        point2.add(xIncrement, 0,0);

        setX();
        setY();

        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(getPoint()), 1, getDustOptions());
        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(point2), 1, getDustOptions());

        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(rotateAroundPoint(getPoint().clone(), 135)), 1, getDustOptions());
        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(rotateAroundPoint(point2.clone(), 135)), 1, getDustOptions());

        changeParticleSettings();
    }

    public Location rotateAroundPoint(Location location, double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);

        double x = angleCos * location.getX() + angleSin * location.getZ();
        double z = -angleSin * location.getX() + angleCos * location.getZ();
        location.setX(x);
        location.setZ(z);

        return location;
    }
}
