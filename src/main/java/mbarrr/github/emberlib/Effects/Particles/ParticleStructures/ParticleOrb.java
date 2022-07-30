package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class ParticleOrb extends ParticleCircle {

    double floor; //minimum y distance
    double ceiling; //maximum y distance
    double yIncrement; //amount to increment Y by

    public ParticleOrb(Plugin instance, double radius, Location location, Particle.DustOptions dustOptions, double floor, double ceiling, double yIncrement, int duration) {
        super(instance, radius, location, dustOptions, duration);
        this.floor = floor;
        this.ceiling = ceiling;
        this.yIncrement = yIncrement;
    }

    @Override
    public void setY(){
        //if Y value goes outside floor or ceiling, inverse the Y increment
        if(point.getY() < floor || point.getY() > ceiling){
            yIncrement*=-1;
        }
        //add the Y increment
        point.add(0, yIncrement, 0);
    }
}
