package org.blackteasea.revival.Listeners;

import org.blackteasea.revival.Data;
import org.blackteasea.revival.functions.Resurrect;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class ReconnectListener implements Listener {
    @EventHandler
    public void uponJoin(PlayerJoinEvent event){
        Server server = Data.getInstance().getJavaPlugin().getServer();
        UUID e = event.getPlayer().getUniqueId();

        //New logic
        if(!Data.getInstance().readEntry(e).isRevived()){
            return;
        }
        //Resurrect
        //Setup
        Location loc = Data.getInstance().readEntry(e).getLoc();
        if (loc == null) {
            //Defaults to set spawn
            loc = server.getOfflinePlayer(e).getRespawnLocation();
        }
        assert server.getPlayer(e) != null;
        Resurrect.resurrect(e, loc);

    }
}
