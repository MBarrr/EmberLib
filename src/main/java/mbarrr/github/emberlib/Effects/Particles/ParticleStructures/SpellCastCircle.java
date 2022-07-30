package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.apache.commons.lang.enums.Enum;
import org.bukkit.*;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spellcaster;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpellCastCircle {

    private Location location;
    private BukkitRunnable bukkitRunnable; //loop runnable
    private long period = 2; //time between loops in ticks
    private Plugin instance; //plugin instance
    private World world; //world to spawn particles in
    private Particle.DustOptions dustOptions;
    private double radius;
    private int duration;
    int red = 255;
    int green = 0;


    private Particle ringParticle = Particle.REDSTONE;
    private Location point;
    private Location point2;

    public SpellCastCircle(Location location, double radius, Plugin instance, int duration){
        this.dustOptions = new Particle.DustOptions(Color.GREEN, 1);
        this.location = location;
        this.duration = duration;
        this.instance = instance;
        this.world = location.getWorld();
        this.radius = radius;
        this.point = location.subtract(0,0,0);
        this.point2 = location.subtract(0,0,0);

        loadRunnable();
        if(duration > 0){
            loadCancellationRunnable();
        }
    }



    public void onTick(){
        this.green = (int) (green + 255/(duration/period));
        this.red = (int) (red - 255/(duration/period));

        Color color = Color.fromRGB(red, green, 0);

        this.dustOptions = new Particle.DustOptions(color, 2);

        for(double z = -radius; z < radius; z=z+0.2){

            double posX = Math.sqrt((radius*radius)-(z*z));
            double negX = -posX;


            Location l = point.clone().add(posX, 0, z);
            Location l2 = point2.clone().add(negX, 0, z);

            world.spawnParticle(ringParticle, l,1, dustOptions);
            world.spawnParticle(ringParticle, l2, 1, dustOptions);
        }
    }


    public void loadRunnable(){

        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                onTick();
            }
        };

        bukkitRunnable.runTaskTimer(instance, 0, period);
    }

    //the code that will be executed in the loop

    public void loadCancellationRunnable(){
        BukkitRunnable cancellationRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                bukkitRunnable.cancel();
            }
        };

        cancellationRunnable.runTaskLater(instance, duration);
    }

    //start the loop
    public void start(){
        bukkitRunnable.runTaskTimer(instance, 0L, period);
    }

    //end the loop
    public void stop(){
        bukkitRunnable.cancel();
    }
}
