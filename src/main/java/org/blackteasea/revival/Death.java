package org.blackteasea.revival;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    @EventHandler
    public void uponDeath(PlayerDeathEvent event) {
        Data data = Data.getInstance();
        Player player = event.getPlayer();
        final Component message = Component.text(
                "OHHHHH NOOOOOOOO! " + player.getName() +
                        " seems to have had his mortal string cut. How unfortunate.");
        Data.getInstance().getJavaPlugin().getServer().broadcast(message);
        data.getJavaPlugin().getLogger().info(player.getName() + " has died and will come back!");
        data.addPlayer(player);
    }


}
