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
//        HashMap<UUID, Boolean> playerList = Data.getInstance().getPlayerList();

//        if(!playerList.containsKey(e)){
//            Data.getInstance().addPlayer(e);
//            return;
//        }
//        if(playerList.get(e)){
//            return;
//        }
        System.out.println("before checker");

        //New logic
        if(!Data.getInstance().readEntry(e)){
            System.out.println("Reconnecting");
            return;
        }


        //Resurrect
        //Setup
        Location loc = Data.getInstance().getDropLocation();
        System.out.println(loc);
        if (loc == null) {
            Player player = server.getPlayer(e);
            World world = server.getWorld(e);
            Location spawnLocation = (world != null) ? world.getSpawnLocation() : null;

            System.out.println("Player: " + player);
            System.out.println("World: " + world);
            System.out.println("Spawn Location: " + spawnLocation);

            if (player != null && world != null) {
                player.teleport(spawnLocation);
            } else {
                System.out.println("Error: One of the required objects is null.");
            }
            return;
        }
        assert server.getPlayer(e) != null;
        TestRes.resurrect(e, loc);

    }
}
