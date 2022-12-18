package mbarrr.github.emberlib;


import mbarrr.github.emberlib.Effects.Particles.ParticleFollowProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

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


    @EventHandler
    public void asd(ProjectileLaunchEvent e){
        ParticleFollowProjectile particleFollowProjectile = new ParticleFollowProjectile(EmberLib.getInstance(), Particle.CAMPFIRE_COSY_SMOKE, e.getEntity());

    }

}
