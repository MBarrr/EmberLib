package mbarrr.github.emberlib.Effects.Particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleLine {


    private Location startLoc;
    private Vector dir;
    private Particle.DustOptions dustOptions;
    private double length;
    private int period;
    private Plugin plugin;
    private double dist;
    private Particle particle;
    private int numTimes;

    public ParticleLine(Location startLoc, Vector dir, double length, int period, Plugin plugin, double dist, Particle particle, Particle.DustOptions dustOptions) {
        this.startLoc = startLoc.clone();
        this.particle = particle;
        this.plugin = plugin;
        this.length = length;
        this.dist = dist;
        this.period = period;
        this.dustOptions = dustOptions;
        this.dir = dir.clone().normalize();
        this.numTimes = (int) (length/dist);
    }

    public void shoot(){
        if(period <= 0){
            shootWithoutPeriod();
        }

        else{
            shootWithPeriod();
        }
    }

    private void shootWithoutPeriod(){
        for(int i = 0; i < numTimes+1; i++){
            shootParticleSegment();
        }
    }

    private void shootWithPeriod(){
        final int[] i = {0};
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(i[0] >= numTimes){
                    cancel();
                    return;
                }
                shootParticleSegment();
                i[0]++;
            }
        };
        runnable.runTaskTimer(plugin, 0, period);
    }

    private void shootParticleSegment(){
        startLoc.add(dir.clone().multiply(dist));
        startLoc.getWorld().spawnParticle(particle, startLoc, 1, dustOptions);
    }

    public Location getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Location startLoc) {
        this.startLoc = startLoc;
    }

    public Vector getDir() {
        return dir;
    }

    public void setDir(Vector dir) {
        this.dir = dir;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
