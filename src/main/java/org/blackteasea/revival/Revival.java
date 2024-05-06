package org.blackteasea.revival;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.GameRule.DO_IMMEDIATE_RESPAWN;
import static org.bukkit.GameRule.SEND_COMMAND_FEEDBACK;

public final class Revival extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Data.getInstance().setJavaPlugin(this);
        getLogger().info("Starting The Revival Project");
        Data.getInstance().setLoader(new Save(Data.getInstance().getPlayerList()));
        Save loader = Data.getInstance().getLoader().getSave();
        if (loader != null){
            System.out.println(loader.playerList.toString());
        }
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Resurrect(), this);
        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, false));
        getServer().getWorlds().forEach(world -> world.setGameRule(DO_IMMEDIATE_RESPAWN, true));
        getLogger().info("Command feedback is now off");
        getServer().addRecipe(Recipe.totemRecipe());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, true));
        Save loader = Data.getInstance().getLoader();
        loader.setSave();
    }
}
