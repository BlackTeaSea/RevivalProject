package org.blackteasea.revival.Listeners;

import org.blackteasea.revival.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.UUID;

/**
 * This class listens for the PlayerDeathEvent in the game.
 * It implements the Listener interface provided by the Bukkit API.
 */
public class DeathListener implements Listener {

    /**
     * This method is triggered when a PlayerDeathEvent occurs.
     * It gets the UUID of the player who died and creates an entry for them in the Data singleton.
     * The entry is created with the player's UUID, a null location, and a status of dead (false).
     *
     * @param event PlayerDeathEvent object representing the event that occurred
     */
    @EventHandler
    public void uponDeath(PlayerDeathEvent event) {
        // Get the UUID of the player who died
        UUID player = event.getPlayer().getUniqueId();

        // Create an entry for the player in the Data singleton
        Data.getInstance().createEntry(player, null, false);
    }
}