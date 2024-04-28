package org.blackteasea.revival;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Death implements Listener {
    @EventHandler
    public void uponDeath(PlayerDeathEvent event){
        Data.getInstance().getLogger().info("I died!");
    }
}
