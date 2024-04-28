package org.blackteasea.revival;

import net.kyori.adventure.text.Component;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.Bukkit.createInventory;

public class Resurrect implements Listener {
    private Data data;
    private Inventory inv;

    private String resurrectee;
    @EventHandler
    public void dropTotem(PlayerDropItemEvent event){
        // If the item dropped is a Totem of Undying
        if (event.getItemDrop().getName().equals("Totem of Undying")){
            openInventory(event.getPlayer());
            initializeItems();
        }
    }

    public Resurrect(){
        data = Data.getInstance();
        inv = createInventory(null, 9, "Resurrect");
    }

    public void initializeItems(){
        // Add the items to the inventory
        for (Player player : data.getPlayerList()){
            inv.addItem(createGUIItem(Material.TOTEM_OF_UNDYING, "Revive the player", player.getName()));
        }
    }

    public @NotNull ItemStack createGUIItem(final Material material, final String name, final String... lore){
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity e){
        e.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e){
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Get the server console
        CommandSender server = Data.getInstance().getJavaPlugin().getServer().getConsoleSender();
        if (clickedItem.getItemMeta().getDisplayName().equals("Revive the player")){
            resurrectee = clickedItem.getItemMeta().getLore().get(0);
            data.getJavaPlugin().getServer().dispatchCommand(server, "gamemode survival " + resurrectee);
            data.removePlayer(data.getPlayerList().get(0));
        }
        else{
            return;
        }

    }



}
