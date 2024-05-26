package org.blackteasea.revival.Listeners;

import jdk.incubator.vector.VectorOperators;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.blackteasea.revival.functions.Cost;
import org.blackteasea.revival.Data;
import org.blackteasea.revival.functions.TestRes;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Objects;
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
        TestRes.resurrect(e, loc);

    }
}
