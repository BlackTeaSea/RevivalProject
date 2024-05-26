package org.blackteasea.revival.Listeners;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.blackteasea.revival.Data;
import org.blackteasea.revival.Events.ItemEntersWaterEvent;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.blackteasea.revival.functions.*;

import java.util.*;


public class ResurrectListener implements Listener {

    private Map<Item, BukkitRunnable> itemTasks = new HashMap<>();

    @EventHandler
    public void dropTotem(PlayerDropItemEvent event) {
        // If the item dropped is a Totem of Undying
        Component Name = event.getItemDrop().getItemStack().displayName();
        String name = PlainTextComponentSerializer.plainText().serialize(Name);
        if (name .equals("[Totem of Revival]")) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (event.getItemDrop().getLocation().getBlock().isLiquid()){
                        cancel();
                        Data.getInstance().getJavaPlugin().getServer()
                                .getPluginManager().callEvent(new ItemEntersWaterEvent(event.getItemDrop(), event.getPlayer()));
                    }
                }
            };
            task.runTaskTimer(Data.getInstance().getJavaPlugin(), 0, 1);
            itemTasks.put(event.getItemDrop(), task);
        }
    }

    @EventHandler
    public void onItemEntersWater(ItemEntersWaterEvent event) {
        if (event.getLocation().getBlock().getType() == Material.WATER){
            Data.getInstance().setDropLocation(event.getLocation());
            Data.getInstance().getGUI().openInventory(event.getPlayer());
            Data.getInstance().getGUI().initializeItems();
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        //The one who invoked the command
        Server server = Data.getInstance().getJavaPlugin().getServer();
        HumanEntity user = e.getWhoClicked();
        Inventory inv = Data.getInstance().getGUIInventory();

        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        //SkullMeta Setup
        final ItemStack clickedItem = e.getCurrentItem();
        assert clickedItem != null;
        SkullMeta clickedSkullMeta = (SkullMeta)clickedItem.getItemMeta();
        UUID clickedPlayer = Objects.requireNonNull(clickedSkullMeta.getOwningPlayer()).getUniqueId();
        if (clickedItem.getType().isAir()) return;

        //Sets location
        Location loc = Data.getInstance().getDropLocation();
        if (loc == null) return;

        UUID target = clickedPlayer;

        //New Function
        if(Data.getInstance().checkEntry(target) && server.getOfflinePlayer(target).isOnline()){
            TestRes.resurrect(target, loc);
        }else{
            Data.getInstance().updateEntry(target, true);
        }
        //Close inventory for the person who clicked
        Data.getInstance().getGUI().closeInventory(e.getWhoClicked());
    }
}


