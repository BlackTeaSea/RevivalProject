package org.blackteasea.revival;

import it.unimi.dsi.fastutil.Hash;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class ReconnectListener implements Listener {
    @EventHandler
    public void uponJoin(PlayerJoinEvent event){
        UUID e = event.getPlayer().getUniqueId();
        HashMap<UUID, Boolean> playerList = Data.getInstance().getPlayerList();
        if(!playerList.containsKey(e)){
            Data.getInstance().addPlayer(e);
            return;
        }
        if(playerList.get(e)){
            return;
        }


        //Resurrect, implies if they're null
        Server server = Data.getInstance().getJavaPlugin().getServer();
        ForwardingAudience audience = Bukkit.getServer();
        Sound revive = Sound.sound(Key.key("block.end_portal.spawn"), Sound.Source.NEUTRAL, 1f, 1f);
        

        Player resurrected = server.getPlayer(e);

        Location loc = Data.getInstance().getDropLocation();
        if (loc == null) {
            server.getPlayer(e).teleport(server.getWorld(e).getSpawnLocation());
            return;
        }

        int newplaytime = (Cost.getPlayTime(e) - Cost.getDeathTime(e))/(60);
        resurrected.teleport(loc);
        resurrected.setStatistic(Statistic.PLAY_ONE_MINUTE, newplaytime);
        resurrected.setStatistic(Statistic.TIME_SINCE_DEATH, 0);
        resurrected.setGameMode(org.bukkit.GameMode.SURVIVAL);
        Data.getInstance().getPlayerList().replace(e, true);
        
        Data.getInstance().setDropLocation(null);

        //Plays a sound and sends a message
        audience.playSound(revive);

        //Component
        final Component respawn = Component.text(resurrected.getName())
                .color(TextColor.color(0x5D3FD3))
                .append(Component.text(" has undergone Revival!", TextColor.color(0xFFFFFF)));
        server.broadcast(respawn);

        Data.getInstance().setDropEvent(null);

    }
}
