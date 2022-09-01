package mbarrr.github.emberlib;


import mbarrr.github.emberlib.Effects.Particles.ParticleFollowProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public final class EmberLib extends JavaPlugin implements Listener {

    Random random = new Random();

    @Override
    public void onEnable() {
        getServer().getPluginCommand("debugcommand").setExecutor(new debugCommand(this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    public static EmberLib getInstance(){
        return (EmberLib) Bukkit.getPluginManager().getPlugin("EmberLib");
    }

    public static Location rotateLocXZ(Location loc, Location axis, double angle) {
        //angle *= -1; // By default, we use counterclockwise rotations. Uncomment to use clcokwise rotations instead.
        angle *= 180 / Math.PI; // By default, angle is in radians. Uncomment to use degrees instead.
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        Vector r1 = new Vector(cos, 0, -sin); // Bukkit vectors need 3 components, so set the y-component to 0
        Vector r2 = new Vector(sin, 0, cos);
        Vector v = loc.clone().subtract(axis).toVector();
        Vector rotated = new Vector(r1.dot(v), 0, r2.dot(v)); // Perform the matrix multiplication
        return rotated.add(axis.toVector()).toLocation(loc.getWorld());
    }

    @EventHandler
    public void asd(ProjectileLaunchEvent e){
        ParticleFollowProjectile particleFollowProjectile = new ParticleFollowProjectile(EmberLib.getInstance(), Particle.CAMPFIRE_COSY_SMOKE, e.getEntity());

    }

}
