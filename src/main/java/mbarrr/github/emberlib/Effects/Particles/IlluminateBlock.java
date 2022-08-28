package mbarrr.github.emberlib.Effects.Particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class IlluminateBlock {

    private double xOffset = 0;
    private double yOffset = 1;
    private double zOffset = 0;

    private int i = 0;
    private Location location;
    private Particle particle;
    private Plugin plugin;
    private int duration;
    private double height;


    /**
     *
     * @param block Block to illuminate
     * @param particle Particle type
     * @param plugin Plugin instance
     * @param duration Duration in seconds
     * @param height Height coefficient, set to 0 for bottom of the block, 1 for top of the block
     */
    public IlluminateBlock(Block block, Particle particle, Plugin plugin, int duration, double height) {
        this.location = block.getLocation();
        this.particle = particle;
        this.plugin = plugin;
        this.duration = duration;
        this.height = height;
    }

    public void start(){

        location.add(0,height,0);
        List<Location> points = new ArrayList<>();

        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(i > duration*20L){
                    cancel();
                    return;
                }

                for(Location loc:points){
                    loc.getWorld().spawnParticle(particle, loc, 0, xOffset, yOffset,zOffset);
                }

                i++;
            }
        };



        BukkitRunnable getPoints = new BukkitRunnable() {
            @Override
            public void run() {
                for(int x = 0; x < 6; x++){
                    for(int z = 0; z < 6; z++){
                        if(!(x == 0 || x == 5 || z == 0 || z == 5)) continue;
                        points.add(location.clone().add(x*0.2, 0, z*0.2));
                    }
                }

                bukkitRunnable.runTaskTimer(plugin, 0, 1);
            }
        };

        getPoints.runTaskAsynchronously(plugin);
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public double getzOffset() {
        return zOffset;
    }

    public void setzOffset(double zOffset) {
        this.zOffset = zOffset;
    }
}
