package mbarrr.github.emberlib.Effects.BlockSplashes;

import mbarrr.github.emberlib.EmberLib;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShockWave implements Listener {

    private List<FallingBlock> fallingBlocks = new ArrayList<>();
    private List<Material> blockTypes;
    private Random random = new Random();
    private Location loc;
    private int radius;
    private Plugin instance;
    private int period;

    public ShockWave(Location location, int radius, Plugin instance, int period){
        instance.getServer().getPluginManager().registerEvents(this, instance);
        this.loc = location;
        this.radius = radius;
        this.instance = instance;
        this.period = period;
    }

    public void addBlockType(Material material){
        if(blockTypes == null) blockTypes = new ArrayList<>();
        blockTypes.add(material);
    }


    public void start(){

        Location startLoc = loc.clone();

        final int[] i = {0};

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(i[0] == radius){
                    cancel();
                    return;
                }

                for(int x = -i[0]; x <= i[0]; x++){
                    for(int z = -i[0]; z <= i[0]; z++){
                        if(z != -i[0] && z != i[0] && x != -i[0] && x != i[0]) continue;


                        Location currentLoc = startLoc.clone().add(x, 0, z);
                        Material mat;

                        if(blockTypes == null) mat = currentLoc.getBlock().getRelative(BlockFace.DOWN).getType();

                        else{
                            int randMaterialIndex = random.nextInt(blockTypes.size());
                            mat = blockTypes.get(randMaterialIndex);
                        }


                        FallingBlock fallingBlock = currentLoc.getWorld().spawnFallingBlock(currentLoc, mat.createBlockData());
                        fallingBlock.setDropItem(false);
                        fallingBlock.setInvulnerable(true);
                        fallingBlock.setHurtEntities(false);
                        fallingBlock.setVelocity(new Vector(0, 0.15, 0));


                        fallingBlocks.add(fallingBlock);
                    }
                }

                i[0]++;
            }
        };

        runnable.runTaskTimer(instance, 0, period);
    }

    /**
     * Triggered when a falling block lands,
     * cancels the event if block belongs to this attack
     * @param e EntityChangeBlockEvent
     */
    @EventHandler
    public void blockLandEvent(EntityChangeBlockEvent e){
        if(!(e.getEntity() instanceof FallingBlock)) return;

        FallingBlock fallingBlock = (FallingBlock) e.getEntity();

        if(!fallingBlocks.contains(fallingBlock)) return;

        e.setCancelled(true);
    }
}
