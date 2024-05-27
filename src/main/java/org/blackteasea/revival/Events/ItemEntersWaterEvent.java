package org.blackteasea.revival.Events;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an event where an item enters water in the game.
 * It extends the Event class provided by the Bukkit API.
 */
public class ItemEntersWaterEvent extends Event {
    // A list of all HandlerList objects, used by the Bukkit API to manage event handlers
    private static final HandlerList HANDLER_LIST = new HandlerList();
    // The location where the event occurred
    private final Location location;
    // The player who caused the event
    private final Player player;

    /**
     * Constructor for the ItemEntersWaterEvent class.
     * @param item The item that entered the water
     * @param player The player who caused the event
     */
    public ItemEntersWaterEvent(Item item, Player player) {
        this.location = item.getLocation();
        this.player = player;
    }
    /**
     * Getter for the location where the event occurred.
     * @return Location object representing the location of the event
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for the player who caused the event.
     * @return Player object representing the player who caused the event
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * This method is required for all events in the Bukkit API.
     * It returns a list of all HandlerList objects.
     * @return HandlerList object containing all handlers for this event
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

}
