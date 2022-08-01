package mbarrr.github.emberlib;

import mbarrr.github.emberlib.Effects.Particles.ParticleStructures.BlockSpellAnimation;
import org.bukkit.Color;
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

        /**
         *         double radius = Double.parseDouble(args[0]);
         *         int duration = Integer.parseInt(args[1]);
         *         Particle particleType = Particle.valueOf(args[2]);
         *         int period = Integer.parseInt(args[3]);
         *         int numParticles = Integer.parseInt(args[4]);
         *
         *         PortalAnimation portalAnimation = new PortalAnimation(EmberLib.getInstance(), period, duration, numParticles, player.getLocation(), radius, particleType);
         */

        int duration = Integer.parseInt(args[0]);
        double radius = Double.parseDouble(args[1]);
        Color startColor = Color.fromRGB(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        Color endColor = Color.fromRGB(Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
        int size = Integer.parseInt(args[8]);


        ///debugcommand 200 2 0 255 0 255 0 0 2

        BlockSpellAnimation blockSpellAnimation = new BlockSpellAnimation(EmberLib.getInstance(), radius, size, player.getLocation(), duration, startColor, endColor);

        return true;
    }
}
