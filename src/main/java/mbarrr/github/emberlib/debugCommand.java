package mbarrr.github.emberlib;

import mbarrr.github.emberlib.Effects.Particles.IlluminateBlock;
import mbarrr.github.emberlib.Effects.Particles.ParticleWhoosh;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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



        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])), Float.parseFloat(args[3]));

        ParticleWhoosh particleWhoosh = new ParticleWhoosh(player.getEyeLocation(), player.getLocation().getDirection(), EmberLib.getInstance(), 5, Particle.REDSTONE, dustOptions);
        particleWhoosh.shoot();


        player.sendMessage("finished");
        return true;
    }
}
