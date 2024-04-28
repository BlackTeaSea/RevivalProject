package org.blackteasea.revival;

import org.bukkit.command.BufferedCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(PlayerDeathEvent event) {
        String player = event.getPlayer().getName();
        Data.getInstance().getJavaPlugin().getLogger().info(player + " had to die!");

    }
}
