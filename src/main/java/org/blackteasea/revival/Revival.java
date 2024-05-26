package org.blackteasea.revival;

//import org.blackteasea.revival.Experimental.Save;
import org.blackteasea.revival.Items.Recipe;
import org.blackteasea.revival.Listeners.DeathListener;
import org.blackteasea.revival.Listeners.ResurrectListener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.GameRule.DO_IMMEDIATE_RESPAWN;
import static org.bukkit.GameRule.SEND_COMMAND_FEEDBACK;

public final class Revival extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Data.getInstance().setJavaPlugin(this);
        getLogger().info("Starting The Revival Project");
        Data.getInstance().initYaml("./plugins/storage.yaml");
        Data.getInstance().load();

        //Data.getInstance().setLoader(new Save(Data.getInstance().getPlayerList()));
//        Save loader = Data.getInstance().getLoader().getSave();
//        if (loader != null){
//            System.out.println(loader.playerList.toString());
//        }

        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new ResurrectListener(), this);

        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, false));
        getServer().getWorlds().forEach(world -> world.setGameRule(DO_IMMEDIATE_RESPAWN, true));
        getLogger().info("Command feedback is now off");
        getServer().addRecipe(Recipe.totemRecipe());

        //Yaml Storage
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getWorlds().forEach(world -> world.setGameRule(SEND_COMMAND_FEEDBACK, true));
        //Save loader = Data.getInstance().getLoader();
        //loader.setSave();

        //Yaml Storage
        Data.getInstance().save();

    }
}
