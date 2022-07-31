package mbarrr.github.emberlib;

import mbarrr.github.emberlib.Effects.Particles.ParticleStructures.SpellCastAnimation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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

        player.sendMessage("runbdsing");

        double radius = Double.parseDouble(args[0]);
        int duration = Integer.parseInt(args[1]);

        SpellCastAnimation spellCastAnimation = new SpellCastAnimation(radius, player.getLocation(), duration, player);

        return true;
    }
}
