package org.blackteasea.revival;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class Death implements Listener {
    @EventHandler
    public void uponDeath(PlayerDeathEvent event) {

        Data data = Data.getInstance();
        UUID player = event.getPlayer().getUniqueId();
        Server server = Data.getInstance().getJavaPlugin().getServer();
//        player.setGameMode(GameMode.SPECTATOR);
        data.getJavaPlugin().getLogger().info(server.getPlayer(player).getName()+ " has died and will come back!");
//        if (data.getPlayerList().get(player) == null) {
//            data.addPlayer(player);
//        }
        data.getPlayerList().replace(player, false);
    }


}
