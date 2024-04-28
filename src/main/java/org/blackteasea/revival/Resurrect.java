package org.blackteasea.revival;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.Bukkit.getOfflinePlayer;

public class Resurrect implements Listener {
    private final Inventory inv;

    //Constructor
    public Resurrect() {
        //GUI scheme
        inv = createInventory(null, 9, "Resurrect");
    }

    @EventHandler
    public void dropTotem(PlayerDropItemEvent event) {
        // If the item dropped is a Totem of Undying
        if (event.getItemDrop().getName().equals("Totem of Undying")) {

            openInventory(event.getPlayer());
            initializeItems();
            event.getItemDrop().remove();
        }

    }

    public void initializeItems() {
        // Add the items to the inventory
        for (Player player : Data.getInstance().getPlayerList()) {
            inv.addItem(createSkullItem(player, player.getName()));
        }
    }

    public void openInventory(final HumanEntity e) {
        e.openInventory(inv);
    }
    public void closeInventory(final HumanEntity e){
        e.closeInventory();
    }

    //Skullwork

    public @NotNull ItemStack createSkullItem(Player player, final String... lore) {
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);
        skull.setLore(Arrays.asList(lore));
        return skull;
    }

    //GUI
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        //The one who invoked the command
        HumanEntity user = e.getWhoClicked();
        ForwardingAudience audience = Bukkit.getServer();
        Sound revive = Sound.sound(Key.key("block.end_portal.spawn"), Sound.Source.NEUTRAL, 1f, 1f);


        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Get the server console
        Server server = Data.getInstance().getInstance().getJavaPlugin().getServer();
        CommandSender serverSend = server.getConsoleSender();

        String resurrectee = clickedItem.getItemMeta().getLore().get(0);
        server.dispatchCommand(serverSend, "gamemode survival " + resurrectee);
        for (Player player : Data.getInstance().getPlayerList()) {
            if (player.getName().equals(resurrectee)) {
                Data.getInstance().removePlayer(player);
                inv.remove(clickedItem);

                //Plays a sound and sends a message
                audience.playSound(revive);

                final Component respawn = Component.text(player.getName())
                        .color(TextColor.color(0x5D3FD3))
                        .append(Component.text(" has undergone Revival!", TextColor.color(0xFFFFFF)));
                server.broadcast(respawn);

            }
        }
        //Close inventory for the person who clicked
        closeInventory(e.getWhoClicked());





    }


}


