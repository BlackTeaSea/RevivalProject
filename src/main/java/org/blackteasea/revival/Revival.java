package org.blackteasea.revival;

import org.bukkit.plugin.java.JavaPlugin;

public final class Revival extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello from Revival");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
