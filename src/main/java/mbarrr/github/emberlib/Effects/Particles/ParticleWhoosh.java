package mbarrr.github.emberlib.Effects.Particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticleWhoosh {

    ///debugcommand 5 0.5 SPELL 5 0

    private final List<ParticleLine> lines = new ArrayList<>();

    public ParticleWhoosh(Location startLoc, Vector dir, Plugin instance, double length, Particle particle, Particle.DustOptions dustOptions){

        for(int i = 0; i < 5; i++){
            ParticleLine particleLine = new ParticleLine(startLoc, dir, length, 0, instance, 0.5, particle, dustOptions);
            lines.add(particleLine);
        }
    }

    public void shoot(){
        for(ParticleLine line:lines){
            line.shoot();
        }
    }

    public List<ParticleLine> getLines(){
        return lines;
    }
}