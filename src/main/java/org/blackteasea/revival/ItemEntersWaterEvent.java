package org.blackteasea.revival;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;

public class ItemEntersWaterEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Item item;
    private Location location;
    private Player player;

    public ItemEntersWaterEvent(Item item, Player player) {
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

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
