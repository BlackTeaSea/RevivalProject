package org.blackteasea.revival;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

import static org.blackteasea.revival.Logic.newSpawnLocation;

public class Resurrect implements Listener {

    @EventHandler
    public void dropTotem(PlayerDropItemEvent event) {
        // If the item dropped is a Totem of Undying
        if (event.getItemDrop().getName().equals("Totem of Revival")) {
            Data.getInstance().getJavaPlugin().getServer().getScheduler().runTaskLater(Data.getInstance().getJavaPlugin(), () -> {
                if (event.getItemDrop().getLocation().getBlock().getType() == Material.WATER){
                    Data.getInstance().setDropEvent(event);
                    Data.getInstance().getGUI().openInventory(event.getPlayer());
                    Data.getInstance().getGUI().initializeItems();
                }
            }, 20L);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        //The one who invoked the command
        HumanEntity user = e.getWhoClicked();
        ForwardingAudience audience = Bukkit.getServer();
        Sound revive = Sound.sound(Key.key("block.end_portal.spawn"), Sound.Source.NEUTRAL, 1f, 1f);
        Inventory inv = Data.getInstance().getGUIInventory();

        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        String resurrected = clickedItem.getItemMeta().getLore().get(0);
        List<Player> playerListCopy = new ArrayList<>(Data.getInstance().getPlayerList());
        for (Player player : playerListCopy) {
            if (player.getName().equals(resurrected)) {
                Location location = player.getLastDeathLocation();
                assert location != null;
                Location newLoc = newSpawnLocation(location);
                Data.getInstance().getJavaPlugin().getLogger().info("New location: " + newLoc);
                player.teleport(newLoc);
                player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                Data.getInstance().removePlayer(player);
                inv.remove(clickedItem);

                //Plays a sound and sends a message
                audience.playSound(revive);
                Server server = Data.getInstance().getJavaPlugin().getServer();
                final Component respawn = Component.text(player.getName())
                        .color(TextColor.color(0x5D3FD3))
                        .append(Component.text(" has undergone Revival!", TextColor.color(0xFFFFFF)));
                server.broadcast(respawn);
                if (Data.getInstance().getDropEvent() != null){
                    Data.getInstance().getDropEvent().getItemDrop().remove();
                }
                Data.getInstance().setDropEvent(null);
            }
        }
        //Remove the items

        //Close inventory for the person who clicked
        Data.getInstance().getGUI().closeInventory(e.getWhoClicked());
    }
}


