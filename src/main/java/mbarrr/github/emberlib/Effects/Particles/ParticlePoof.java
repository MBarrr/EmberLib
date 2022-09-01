package mbarrr.github.emberlib.Effects.Particles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class ParticlePoof {

    private final BlockData blockData;
    private final Location location;
    private final Plugin plugin;

    public ParticlePoof(LivingEntity entity, Plugin plugin, Material material){
        this(entity.getEyeLocation().add(entity.getLocation().getDirection().clone()), plugin, material);
    }
    public ParticlePoof(Location location, Plugin plugin, Material material) {
        this.location = location;
        this.blockData = material.createBlockData();
        this.plugin = plugin;
    }

    public void start(){
        location.getWorld().spawnParticle(Particle.BLOCK_DUST, location, 40, 0, 0, 0, blockData);
    }
}
