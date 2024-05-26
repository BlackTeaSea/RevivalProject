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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.Statistic;
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
//        HashMap<UUID, Boolean> playerList = Data.getInstance().getPlayerList();

//        if(!playerList.containsKey(e)){
//            Data.getInstance().addPlayer(e);
//            return;
//        }
//        if(playerList.get(e)){
//            return;
//        }

        //New logic
        if(!Data.getInstance().checkRevived(e)){
            return;
        }


        //Resurrect

        //Setup
        Location loc = Data.getInstance().getDropLocation();
        if (loc == null) {
            Objects.requireNonNull(server.getPlayer(e)).teleport(Objects.requireNonNull(server.getWorld(e)).getSpawnLocation());
            return;
        }
        assert server.getPlayer(e) != null;
        TestRes.resurrect(e, loc);

    }
}
