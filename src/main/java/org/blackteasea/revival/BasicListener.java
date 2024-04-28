package org.blackteasea.revival;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.PlayerDeathEvent;

public class BasicListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(PlayerDeathEvent event) {
        //Get name of the player
        String player = event.getPlayer().getName();

        //Says the name of the player, and "had to die" in console
        Data.getInstance().getJavaPlugin().getLogger().info(player + " had to die!");

        final Component message = Component.text("OHHHHH NOOOOOOOO! " + player + " seems to have had his mortal string cut. How unfortunate.");
        Data.getInstance().getJavaPlugin().getServer().broadcast(message);
    }
}
