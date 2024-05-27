package org.blackteasea.revival;

import org.blackteasea.revival.Items.Recipe;
import org.blackteasea.revival.Listeners.DeathListener;
import org.blackteasea.revival.Listeners.ReconnectListener;
import org.blackteasea.revival.Listeners.ResurrectListener;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import static org.bukkit.GameRule.DO_IMMEDIATE_RESPAWN;
import static org.bukkit.GameRule.SEND_COMMAND_FEEDBACK;

/**
 * This is the main class for the Revival plugin.
 * It extends the JavaPlugin class provided by the Bukkit API.
 */
public final class Revival extends JavaPlugin {

    /**
     * This method is called when the plugin is enabled.
     * It registers the Storage class for serialization, sets the JavaPlugin instance in the Data singleton,
     * logs a startup message, and loads the death list from a YAML file.
     * It also registers the DeathListener, ResurrectListener, and ReconnectListener classes as event listeners.
     * It sets the SEND_COMMAND_FEEDBACK and DO_IMMEDIATE_RESPAWN game rules for all worlds,
     * logs a message indicating that command feedback is off, and adds the custom totem recipe to the server.
     */
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

    /**
     * This method is called when the plugin is disabled.
     * It sets the SEND_COMMAND_FEEDBACK game rule to true for all worlds and saves the death list to a YAML file.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, true));

        Data.getInstance().save();

    }
}
