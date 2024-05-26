package org.blackteasea.revival.Experimental;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ReconnectEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    //Above here

    private Item item;
    private Location location;
    private Player player;

    public ReconnectEvent(UUID uuid, Location location) {
        this.item = item;
        this.location = item.getLocation();
        this.player = player;
    }

    public Item getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }

    public Player getPlayer(){
        return player;
    }

    //Important
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}

