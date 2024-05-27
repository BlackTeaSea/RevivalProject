package org.blackteasea.revival.Listeners;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.blackteasea.revival.Data;
import org.blackteasea.revival.Events.ItemEntersWaterEvent;
import org.blackteasea.revival.Storage;
import org.bukkit.*;
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

/**
 * This class listens for various events related to the resurrection process in the game.
 * It implements the Listener interface provided by the Bukkit API.
 */
public class ResurrectListener implements Listener {

    /**
     * This method is triggered when a PlayerDropItemEvent occurs.
     * It checks if the dropped item is a "Totem of Revival".
     * If it is, it starts a task that checks if the item enters a liquid block.
     * If the item enters a liquid block, it cancels the task and triggers an ItemEntersWaterEvent.
     *
     * @param event PlayerDropItemEvent object representing the event that occurred
     */
    @EventHandler
    public void dropTotem(PlayerDropItemEvent event) {
        // If the item dropped is a Totem of Revival
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
        }
    }

    /**
     * This method is triggered when an ItemEntersWaterEvent occurs.
     * It checks if the item entered a water block.
     * If it did, it sets the drop location in the Data singleton and opens the GUI for the player.
     *
     * @param event ItemEntersWaterEvent object representing the event that occurred
     */
    @EventHandler
    public void onItemEntersWater(ItemEntersWaterEvent event) {
        if (event.getLocation().getBlock().getType() == Material.WATER){
            Data.getInstance().setDropLocation(event.getLocation());
            Data.getInstance().getGUI().openInventory(event.getPlayer());
            Data.getInstance().getGUI().initializeItems();
        }
    }

    /**
     * This method is triggered when an InventoryClickEvent occurs.
     * It checks if the clicked inventory is the GUI inventory.
     * If it is, it cancels the event, checks if the clicked item is a skull, and resurrects the player associated with the skull.
     * If the player is not online, it updates the player's entry in the Data singleton and broadcasts a message to the server.
     * Finally, it closes the inventory for the player who clicked.
     *
     * @param e InventoryClickEvent object representing the event that occurred
     */
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        //The one who invoked the command
        Server server = Data.getInstance().getJavaPlugin().getServer();
        Inventory inv = Data.getInstance().getGUIInventory();
        ForwardingAudience audience = Bukkit.getServer();
        net.kyori.adventure.sound.Sound softRevive = net.kyori.adventure.sound.Sound.sound(Key.key("block.amethyst_cluster.break"), Sound.Source.NEUTRAL, 0.65f, 0.83f);

        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        //SkullMeta Setup
        final ItemStack clickedItem = e.getCurrentItem();
        assert clickedItem != null;
        SkullMeta clickedSkullMeta = (SkullMeta)clickedItem.getItemMeta();
        UUID target = Objects.requireNonNull(clickedSkullMeta.getOwningPlayer()).getUniqueId();
        if (clickedItem.getType().isAir()) return;

        //Sets location
        Location loc = Data.getInstance().getDropLocation();
        if (loc == null) return;

        //New Function
        if(Data.getInstance().checkEntry(target) && server.getOfflinePlayer(target).isOnline()){
            Resurrect.resurrect(target, loc);
        }else{
            Data.getInstance().updateEntry(target, new Storage(loc, true));
            audience.playSound(softRevive);
            final Component respawn = Component.text(Objects.requireNonNull(server.getOfflinePlayer(target).getName()))
                    .color(TextColor.color( 0x7b6db3 ))
                    .append(Component.text(" will be revived on next join!", TextColor.color(0x818589)));
            server.broadcast(respawn);
        }
        //Close inventory for the person who clicked
        Data.getInstance().getGUI().closeInventory(e.getWhoClicked());
    }
}


