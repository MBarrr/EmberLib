package mbarrr.github.emberlib.Effects.BlockSplashes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSplash implements Listener {


    private List<FallingBlock> fallingBlocks = new ArrayList<>();

    private Random random = new Random();
    private int numBlocks;
    private Location startLoc;
    private double velocity;
    private List<Material> blockTypes;

    /**
     * Creates a block splash at the location
     * @param numBlocks Number of blocks to spawn
     * @param startLoc Location to spawn block splash
     * @param velocity Velocity of spawned blocks, 0.3 as default
     * @param blockTypes Type of blocks to be spawned, or null for type of block at location
     * @param instance Plugin instance
     */
    public BlockSplash(int numBlocks, Location startLoc, double velocity, List<Material> blockTypes, JavaPlugin instance) {

        this.blockTypes = blockTypes;

        if(blockTypes == null){
            this.blockTypes = new ArrayList<>();
            this.blockTypes.add(startLoc.getBlock().getType());
        }

        this.numBlocks = numBlocks;
        this.startLoc = startLoc.add(0,1,0);
        this.velocity = velocity;


        instance.getServer().getPluginManager().registerEvents(this, instance);



        spawnBlocks();
    }

    private void spawnBlocks(){
        for(int i = 0; i < numBlocks; i++){

            int randMaterialIndex = random.nextInt(blockTypes.size());

            Material randMaterial = blockTypes.get(randMaterialIndex);

            double randx = random.nextInt(20)-10;
            double randz = random.nextInt(20)-10;

            Vector vec = new Vector(randx, 10, randz);
            vec.normalize();

            vec.multiply(velocity);

            FallingBlock fallingBlock = startLoc.getWorld().spawnFallingBlock(startLoc, randMaterial.createBlockData());
            fallingBlock.setDropItem(false);
            fallingBlock.setInvulnerable(true);
            fallingBlock.setHurtEntities(false);

            fallingBlocks.add(fallingBlock);
        }
    }

    @EventHandler
    private void blockLandEvent(EntityChangeBlockEvent e){
        if(!(e.getEntity() instanceof FallingBlock)) return;

        FallingBlock fallingBlock = (FallingBlock) e.getEntity();

        if(!fallingBlocks.contains(fallingBlock)) return;

        fallingBlocks.remove(fallingBlock);
        e.setCancelled(true);
    }
}
