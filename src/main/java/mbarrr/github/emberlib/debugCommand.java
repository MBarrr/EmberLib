package mbarrr.github.emberlib;

import mbarrr.github.emberlib.Effects.Particles.IlluminateBlock;
import mbarrr.github.emberlib.Effects.Particles.ParticlePoof;
import mbarrr.github.emberlib.Effects.Particles.ParticleWhoosh;
import mbarrr.github.emberlib.Util.UTIL;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;
import java.util.List;

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

        String str = UTIL.serializeItem(player.getEquipment().getItemInMainHand());

        ItemStack item = UTIL.deserializeItem(str);

        player.getInventory().addItem(item);

        Bukkit.getConsoleSender().sendMessage(str);

        player.sendMessage("finished");
        return true;
    }
}
