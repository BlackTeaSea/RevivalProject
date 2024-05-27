package org.blackteasea.revival.Listeners;

import org.blackteasea.revival.Data;
import org.blackteasea.revival.functions.Resurrect;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.Objects;
import java.util.UUID;

/**
 * This class listens for the PlayerJoinEvent in the game.
 * It implements the Listener interface provided by the Bukkit API.
 */
public class ReconnectListener implements Listener {

    /**
     * This method is triggered when a PlayerJoinEvent occurs.
     * It gets the UUID of the player who joined and checks if they are marked as dead in the Data singleton.
     * If the player is not dead, it resurrects the player at their stored location.
     * If the stored location is null, it defaults to the player's current location.
     *
     * @param event PlayerJoinEvent object representing the event that occurred
     */
    @EventHandler
    public void uponJoin(PlayerJoinEvent event){
        // Get the server instance from the Data singleton
        Server server = Data.getInstance().getJavaPlugin().getServer();

        // Get the UUID of the player who joined
        UUID e = event.getPlayer().getUniqueId();

        // If the player is marked as dead in the Data singleton, return without doing anything
        if(Data.getInstance().readEntry(e).isDead()){
            return;
        }
        // Get the stored location of the player from the Data singleton
        Location loc = Data.getInstance().readEntry(e).getLoc();

        // If the stored location is null, default to the player's current location
        if (loc == null) {
            //Defaults to set spawn
            loc = Objects.requireNonNull(server.getPlayer(e)).getLocation();
        }

        // Ensure the player instance is not null
        assert server.getPlayer(e) != null;

        // Resurrect the player at the specified location
        Resurrect.resurrect(e, loc);

    }
}
