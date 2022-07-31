package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleStructure {

    private BukkitRunnable bukkitRunnable; //loop runnable
    private BukkitRunnable cancel;
    private long period = 1; //time between loops in ticks
    private Plugin instance; //plugin instance
    private World world; //world to spawn particles in
    private Particle.DustOptions dustOptions; //particle settings
    private Location centerPoint; //centre point of formation
    private double radius; //distance from the centre on the X axis that the new point starts from
    protected double xIncrement; //amount to increment X by
    private Location point; //our point to work with

    private int duration;

    public ParticleStructure(Plugin instance, double radius, Particle.DustOptions dustOptions, Location centerPoint, int duration){
        this.centerPoint = centerPoint;
        this.dustOptions = dustOptions;
        this.instance = instance;
        this.radius = radius;
        this.world = this.centerPoint.getWorld();
        this.duration = duration;
        this.xIncrement = 0.1;
        this.point = new Location(world, 0, 0, -radius);



        loadRunnable();

        if(duration != 0){
            cancellationRunnable();
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

    public void cancellationRunnable(){
        cancel = new BukkitRunnable() {
            @Override
            public void run() {
                bukkitRunnable.cancel();
            }
        };
        cancel.runTaskLater(instance, duration);
    }

    //the code that will be executed in the loop
    public void onTick(){

        point.add(0, 0, xIncrement);
        setX();
        setY();
        changeParticleSettings();


        instance.getServer().broadcastMessage(point.getX() + " " + point.getY() + " " + point.getZ());
        world.spawnParticle(Particle.REDSTONE, centerPoint.clone().add(point), 1, dustOptions);

    }

    public void setX(){}

    public void setY(){}

    public void changeParticleSettings(){}

    protected int getDuration() {
        return duration;
    }

    //start the loop
    public void start(){
        bukkitRunnable.runTaskTimer(instance, 0L, period);
    }

    //end the loop
    public void stop(){
        bukkitRunnable.cancel();
        cancel.cancel();;
    }

    public BukkitRunnable getBukkitRunnable() {
        return bukkitRunnable;
    }

    public BukkitRunnable getCancel() {
        return cancel;
    }

    public long getPeriod() {
        return period;
    }

    public Plugin getInstance() {
        return instance;
    }

    public World getWorld() {
        return world;
    }

    public Particle.DustOptions getDustOptions() {
        return dustOptions;
    }

    public Location getCenterPoint() {
        return centerPoint;
    }

    public void setCentrePoint(Location location){
        this.centerPoint = location;
    }

    public double getRadius() {
        return radius;
    }

    public double getxIncrement() {
        return xIncrement;
    }

    public Location getPoint() {
        return point;
    }
}
