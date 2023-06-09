package mbarrr.github.emberlib;

import mbarrr.github.emberlib.Effects.BlockSplashes.ShockWave;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class debugCommand implements CommandExecutor {

    JavaPlugin instance;
    ShockWave shockWave;

    public debugCommand(JavaPlugin instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("called command");

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;


        if(args[0].equalsIgnoreCase("create")){

            int radius = Integer.parseInt(args[1]);
            int period = Integer.parseInt(args[2]);

            shockWave = new ShockWave(player.getLocation().clone(), radius, instance, period);
        }

        else if(args[0].equalsIgnoreCase("addMaterial")){
            Material mat = Material.valueOf(args[1]);
            shockWave.addBlockType(mat);
        }

        else if(args[0].equalsIgnoreCase("start")){
            shockWave.start();
        }


        return true;
    }
}
