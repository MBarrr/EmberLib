package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import mbarrr.github.emberlib.EmberLib;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class SpellCastAnimation extends ParticleStructure {

    private Location point2;
    private Particle.DustOptions dustOptions;
    private int green = 0;
    private int red = 255;
    private SpellCastCircle spellCastCircle;
    private LivingEntity owner;


    public SpellCastAnimation(JavaPlugin instance, double radius, Location location, int duration, LivingEntity owner) {
        super(instance, radius, new Particle.DustOptions(Color.GREEN, 2), location, duration);

        this.owner = owner;



        this.dustOptions = new Particle.DustOptions(Color.GREEN, 2);

        getPoint().add(radius,0,0);
        point2 = getPoint().clone();

        this.spellCastCircle = new SpellCastCircle(location, radius, getInstance(), duration, owner);
    }

    @Override
    public void onTick(){

        if(owner != null){
            setPoint(owner.getLocation());

        }

        if (getPoint().getZ() < -getRadius() || getPoint().getZ() > getRadius()) {
            xIncrement *= -1;
        }

        getPoint().add(0, 0, xIncrement);
        point2.add(xIncrement, 0,0);

        setX();
        setY();
        changeParticleSettings();

        this.green = (int) (green + 255/(getDuration()/getPeriod()));
        this.red = (int) (red - 255/(getDuration()/getPeriod()));

        Color color = Color.fromRGB(red, green, 0);
        this.dustOptions = new Particle.DustOptions(color, 2);

        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(getPoint()), 1, dustOptions);
        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(point2), 1, dustOptions);

        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(rotateAroundPoint(getPoint(), 90)), 1, dustOptions);
        getWorld().spawnParticle(Particle.REDSTONE, getCenterPoint().clone().add(rotateAroundPoint(point2, 90)), 1, dustOptions);
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


    @Override
    public void stop() {
        super.stop();
        spellCastCircle.stop();
    }
}
