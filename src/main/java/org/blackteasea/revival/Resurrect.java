package org.blackteasea.revival;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Resurrect implements Listener {

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
        HumanEntity user = e.getWhoClicked();
        ForwardingAudience audience = Bukkit.getServer();
        Sound revive = Sound.sound(Key.key("block.end_portal.spawn"), Sound.Source.NEUTRAL, 1f, 1f);
        Inventory inv = Data.getInstance().getGUIInventory();

        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Checks if they got the stuff


        String resurrected = PlainTextComponentSerializer.plainText().serialize(clickedItem.getItemMeta().lore().get(0));
        List<Player> playerListCopy = new ArrayList<>(Data.getInstance().getPlayerList());
        for (Player player : playerListCopy) {
            if (player.getName().equals(resurrected)) {
                Location loc = Data.getInstance().getDropLocation();
                if (loc == null) return;
                player.teleport(loc);
                player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                Data.getInstance().removePlayer(player);
                inv.remove(clickedItem);
                Data.getInstance().setDropLocation(null);

                //Plays a sound and sends a message
                audience.playSound(revive);
                Server server = Data.getInstance().getJavaPlugin().getServer();

                //Component
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


