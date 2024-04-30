package org.blackteasea.revival;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    @EventHandler
    public void uponDeath(PlayerDeathEvent event) {

        Data data = Data.getInstance();
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
        data.getJavaPlugin().getLogger().info(player.getName() + " has died and will come back!");
        if (!data.getPlayerList().contains(player)) {
            data.addPlayer(player);
        }
    }


}
