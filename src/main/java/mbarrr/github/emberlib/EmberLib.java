package mbarrr.github.emberlib;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmberLib extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginCommand("debugcommand").setExecutor(new debugCommand(this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    public static EmberLib getInstance(){
        return (EmberLib) Bukkit.getPluginManager().getPlugin("EmberLib");
    }
}
