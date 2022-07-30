package mbarrr.github.emberlib.Effects.Particles.SplashRings;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class TimeShrinkSplashRing extends SplashRing{
    /**
     * SplashRing animation/particle effect. It is essentially a splash lingering potion with extra options
     *
     * @param location       Location to spawn, the centre of the effect will be at this location
     * @param sender         Optional, sets the source of the effect cloud
     * @param playerToFollow Optional, the effect cloud will follow the player
     * @param radius         Radius of the area of effect
     * @param color          Color of the particles
     * @param instance       Plugin instance
     * @param duration       Time in ticks for the cloud to despawn
     * @param particleType   Type of particle
     */
    public TimeShrinkSplashRing(Location location, @Nullable LivingEntity sender, @Nullable LivingEntity playerToFollow, float radius, Color color, Plugin instance, int duration, Particle particleType, int setRadiusPerTick) {
        super(location, sender, playerToFollow, radius, color, instance, duration, particleType);

        getAreaEffectCloud().setRadiusPerTick(setRadiusPerTick);
    }
}