package org.blackteasea.revival;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Resurrect implements Listener {

    @EventHandler
    public void dropTotem(PlayerDropItemEvent itemEvent){
        Data.getInstance().getJavaPlugin().getLogger().info("lmao");
        Data.getInstance().getJavaPlugin().getLogger().info(itemEvent.getItemDrop().getName());
    }
}
