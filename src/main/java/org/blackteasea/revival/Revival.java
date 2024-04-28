package org.blackteasea.revival;


import org.bukkit.plugin.java.JavaPlugin;

public final class Revival extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Data.getInstance().setJavaPlugin(this);
        getLogger().info("Starting The Revival Project");
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new BasicListener(), this);
        getServer().getPluginManager().registerEvents(new Resurrect(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
