package org.blackteasea.revival.Listeners;

import org.blackteasea.revival.Data;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;
import java.util.UUID;

public class DeathListener implements Listener {
    @EventHandler
    public void uponDeath(PlayerDeathEvent event) {
        UUID player = event.getPlayer().getUniqueId();

        Data.getInstance().createEntry(player, null, false);

        //Debug

    }
}