package org.blackteasea.revival;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Resurrect implements Listener {

    @EventHandler
    public void dropTotem(PlayerDropItemEvent event){
        Data data = Data.getInstance();
        if (event.getItemDrop().getName().equals("Totem of Undying")){
            CommandSender server = Data.getInstance().getJavaPlugin().getServer().getConsoleSender();
            String playername = data.getPlayerList().get(0).getName();

            //Changes Gamemode for player
            data.getJavaPlugin().getServer().dispatchCommand(server, "gamemode survival " + playername);
            data.removePlayer(data.getPlayerList().get(0));
        }
    }
}
