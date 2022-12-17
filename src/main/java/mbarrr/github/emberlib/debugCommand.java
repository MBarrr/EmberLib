package mbarrr.github.emberlib;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class debugCommand implements CommandExecutor {

    ArmorStand armorStand;
    BukkitRunnable runnable;

    double xInc = 0;
    double yInc = 0.01;
    double zInc = 0;

    Particle[] particles = Particle.values();
    JavaPlugin instance;

    public debugCommand(JavaPlugin instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("called command");

        if(!(sender instanceof Player)) return false;



        sender.sendMessage("finished");
        return true;
    }
}
