package mbarrr.github.emberlib;


import org.bukkit.plugin.java.JavaPlugin;

public final class EmberLib extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginCommand("debugcommand").setExecutor(new debugCommand(this));

    }

    @Override
    public void onDisable() {

    }
}
