package org.blackteasea.revival;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BasicListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(PlayerDeathEvent event) {
        //Get name of the player
        Player player = event.getPlayer();
        String playername = player.getName();

        //Says the name of the player, and "had to die" in console
        Data.getInstance().getJavaPlugin().getLogger().info(playername + " had to die!");

//        final Component message = Component.text("OHHHHH NOOOOOOOO! " + playername + " seems to have had his mortal string cut. How unfortunate.");
//
//        Data.getInstance().getJavaPlugin().getServer().broadcast(message);
//        CommandSender server = Data.getInstance().getJavaPlugin().getServer().getConsoleSender();
//
//        //Changes Gamemode for player
//        Data.getInstance().getJavaPlugin().getServer().dispatchCommand(server, "gamemode spectator " + playername);

    }
}
