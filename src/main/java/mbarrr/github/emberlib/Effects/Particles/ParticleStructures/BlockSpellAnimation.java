package mbarrr.github.emberlib.Effects.Particles.ParticleStructures;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

public class BlockSpellAnimation extends ParticleSquareCorner{

    private final Color startColor;
    private final Color endColor;
    private final int particleSize;
    private int red;
    private int green;
    private int blue;

    public BlockSpellAnimation(Plugin instance, double radius, int particleSize, Location location, int duration, Color startColor, Color endColor) {
        super(instance, radius, location, duration, new Particle.DustOptions(startColor, particleSize));

        this.particleSize = particleSize;
        this.endColor = endColor;
        this.startColor = startColor;

        red = startColor.getRed();
        green = startColor.getGreen();
        blue = startColor.getBlue();
    }


    @Override
    public void changeParticleSettings() {
        super.changeParticleSettings();

        this.green = (int) (green + ((endColor.getGreen()-startColor.getGreen())/(getDuration()/getPeriod())));
        this.red = (int) (red + ((endColor.getRed()-startColor.getRed())/(getDuration()/getPeriod())));
        this.blue = (int) (blue + ((endColor.getBlue()-startColor.getBlue())/(getDuration()/getPeriod())));

        Color color = Color.fromRGB(red, green, blue);
        Particle.DustOptions dustOptions = new Particle.DustOptions(color, particleSize);
        setDustOptions(dustOptions);
    }
}
