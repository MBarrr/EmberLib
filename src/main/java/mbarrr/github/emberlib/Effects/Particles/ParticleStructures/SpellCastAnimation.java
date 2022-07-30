package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import mbarrr.github.emberlib.EmberLib;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class SpellCastAnimation extends ParticleStructure {

    private Location point2;
    private Particle.DustOptions dustOptions;
    private int green = 0;
    private int red = 255;

    public SpellCastAnimation(double radius, Location location, int duration) {
        super(EmberLib.getInstance(), radius, new Particle.DustOptions(Color.GREEN, 2), location, duration);

        this.dustOptions = new Particle.DustOptions(Color.GREEN, 2);

        point.add(radius,0,0);
        point2 = point.clone();

        SpellCastCircle spellCastCircle = new SpellCastCircle(location, radius, instance, duration);
    }

    @Override
    public void onTick(){
        if (point.getZ() < -radius || point.getZ() > radius) {
            xIncrement *= -1;
        }

        point.add(0, 0, xIncrement);
        point2.add(xIncrement, 0,0);

        setX();
        setY();
        changeParticleSettings();

        this.green = (int) (green + 255/(getDuration()/period));
        this.red = (int) (red - 255/(getDuration()/period));

        Color color = Color.fromRGB(red, green, 0);
        this.dustOptions = new Particle.DustOptions(color, 2);

        world.spawnParticle(Particle.REDSTONE, centerPoint.clone().add(point), 1, dustOptions);
        world.spawnParticle(Particle.REDSTONE, centerPoint.clone().add(point2), 1, dustOptions);

        world.spawnParticle(Particle.REDSTONE, centerPoint.clone().add(rotateAroundPoint(point, 90)), 1, dustOptions);
        world.spawnParticle(Particle.REDSTONE, centerPoint.clone().add(rotateAroundPoint(point2, 90)), 1, dustOptions);
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
