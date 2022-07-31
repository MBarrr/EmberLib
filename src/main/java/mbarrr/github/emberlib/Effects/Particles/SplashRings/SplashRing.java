package mbarrr.github.emberlib.Effects.Particles.SplashRings;

import org.bukkit.*;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SplashRing {

    private Location location;
    private BukkitRunnable bukkitRunnable; //loop runnable
    private long period; //time between loops in ticks
    private Plugin instance; //plugin instance
    private World world; //world to spawn particles in
    private float radius;
    private LivingEntity sender;
    private Color color;
    private int duration;
    private Particle particleType;
    private LivingEntity playerToFollow;
    private AreaEffectCloud areaEffectCloud;
    private List<PotionEffect> potionEffects;

    /**
     * SplashRing animation/particle effect. It is essentially a splash lingering potion with extra options
     * @param location Location to spawn, the centre of the effect will be at this location
     * @param sender Optional, sets the source of the effect cloud
     * @param playerToFollow Optional, the effect cloud will follow the player
     * @param radius Radius of the area of effect
     * @param color Color of the particles
     * @param instance Plugin instance
     * @param duration Time in ticks for the cloud to despawn
     * @param particleType Type of particle
     */
    public SplashRing(Location location, @Nullable LivingEntity sender, @Nullable LivingEntity playerToFollow, float radius, Color color, Plugin instance, int duration, Particle particleType, List<PotionEffect> potionEffects){
        this.instance = instance;
        this.sender = sender;
        this.playerToFollow = playerToFollow;
        this.location = location;
        this.world = location.getWorld();
        this.period = 1;
        this.potionEffects = potionEffects;
        this.radius = radius;
        this.duration = duration;
        this.color = color;
        this.particleType = particleType;

        spawn();

        if(this.playerToFollow != null){
            loadRunnable();
            start();
        }
    }

    public void spawn(){

        try{
            //Set area effect cloud properties
            areaEffectCloud = (AreaEffectCloud) world.spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
            areaEffectCloud.setParticle(particleType);
            areaEffectCloud.setRadius(radius);
            areaEffectCloud.setColor(color);
            areaEffectCloud.setDuration(duration);

            for(PotionEffect potionEffect:potionEffects){
                areaEffectCloud.addCustomEffect(potionEffect, false);
            }

            if(sender != null){
                areaEffectCloud.setSource(sender);
            }

            areaEffectCloud.setWaitTime(20);

        }catch(Exception e){
            Bukkit.broadcastMessage(e.getMessage());
        }
    }

    //Teleport the areaEffectCloud to the owner every tick
    public void onTick(){
        areaEffectCloud.teleport(playerToFollow);
    }

    //Load the bucketrunnable
    public void loadRunnable(){
        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                onTick();
            }
        };
    }

    //the code that will be executed in the loop

    //start the loop
    public void start(){
        //Run the runnable
        bukkitRunnable.runTaskTimer(instance, 0L, period);

        //Schedule the runnable to be cancelled at the end of the thing
        BukkitRunnable stopRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                stop();
            }
        };
        stopRunnable.runTaskLater(instance, duration);
    }

    //end the loop
    public void stop(){
        bukkitRunnable.cancel();
    }

    protected AreaEffectCloud getAreaEffectCloud(){
        return areaEffectCloud;
    }

}
