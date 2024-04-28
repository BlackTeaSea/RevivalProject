package org.blackteasea.revival;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Death implements Listener {
    @EventHandler
    public void uponDeath(PlayerDeathEvent event){
        Data data = Data.getInstance();
        data.getJavaPlugin().getLogger().info("has died and will come back!");
        data.addPlayer(event.getPlayer());
//        data.getJavaPlugin().getLogger().info(data.getPlayerList().toString());
    }
}
