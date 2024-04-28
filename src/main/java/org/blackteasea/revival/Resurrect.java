package org.blackteasea.revival;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.Bukkit.createInventory;

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
        }
    }

    public void initializeItems() {
        // Add the items to the inventory
        for (Player player : Data.getInstance().getPlayerList()) {
            inv.addItem(createSkullItem(player,"Revive the player", player.getName()));
        }
    }

    public void openInventory(final HumanEntity e) {
        e.openInventory(inv);
    }

    //Skullwork

    public @NotNull ItemStack createSkullItem(Player player, final String name, final String... lore) {
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
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Get the server console
        CommandSender server = Data.getInstance().getInstance().getJavaPlugin().getServer().getConsoleSender();
        String resurrectee = clickedItem.getItemMeta().getLore().get(0);
        Data.getInstance().getJavaPlugin().getServer().dispatchCommand(server, "gamemode survival " + resurrectee);
        Data.getInstance().getJavaPlugin().getServer().dispatchCommand(server, "");
        for (Player player : Data.getInstance().getPlayerList()) {
            if (player.getName().equals(resurrectee)) {
                Data.getInstance().removePlayer(player);
                inv.remove(clickedItem);
            }
        }

    }


}


