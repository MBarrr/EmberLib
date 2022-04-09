package mbarrr.github.emberlib;

import mbarrr.github.emberlib.BlockSplashes.BlockSplash;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class debugCommand implements CommandExecutor {

    JavaPlugin instance;

    public debugCommand(JavaPlugin instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("called command");

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        List<Material> blockTypes = new ArrayList<>();
        blockTypes.add(Material.DIRT);
        blockTypes.add(Material.GRASS_BLOCK);
        blockTypes.add(Material.GRAVEL);
        blockTypes.add(Material.STONE);

        BlockSplash blockSplash = new BlockSplash(10, player.getLocation().subtract(0, 1, 0), 1, blockTypes, instance);

        return true;
    }
}
