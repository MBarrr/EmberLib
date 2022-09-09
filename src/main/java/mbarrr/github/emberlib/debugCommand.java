package mbarrr.github.emberlib;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class debugCommand implements CommandExecutor {

    Particle[] particles = Particle.values();
    JavaPlugin instance;

    public debugCommand(JavaPlugin instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("called command");

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        player.sendMessage("starting");

        ItemStack item = player.getEquipment().getItemInMainHand();

        player.getInventory().addItem(ItemStack.deserialize(item.serialize()));



        player.sendMessage("finished");
        return true;
    }
}
