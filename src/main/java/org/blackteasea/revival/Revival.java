package org.blackteasea.revival;

//import org.blackteasea.revival.Experimental.Save;
import org.blackteasea.revival.Items.Recipe;
import org.blackteasea.revival.Listeners.DeathListener;
import org.blackteasea.revival.Listeners.ReconnectListener;
import org.blackteasea.revival.Listeners.ResurrectListener;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.GameRule.DO_IMMEDIATE_RESPAWN;
import static org.bukkit.GameRule.SEND_COMMAND_FEEDBACK;

public final class Revival extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigurationSerialization.registerClass(Storage.class, "Storage");
        Data.getInstance().setJavaPlugin(this);
        getLogger().info("Starting The Revival Project");
        Data.getInstance().load();



        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new ResurrectListener(), this);
        getServer().getPluginManager().registerEvents(new ReconnectListener(), this);

        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, false));
        getServer().getWorlds().forEach(world -> world.setGameRule(DO_IMMEDIATE_RESPAWN, true));
        getLogger().info("Command feedback is now off");
        getServer().addRecipe(Recipe.totemRecipe());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, true));

        Data.getInstance().save();

    }
}
