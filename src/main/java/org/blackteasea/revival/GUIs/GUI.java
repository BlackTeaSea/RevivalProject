package org.blackteasea.revival.GUIs;

import org.blackteasea.revival.Storage;
import org.blackteasea.revival.Data;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This class represents a Graphical User Interface (GUI) in the game.
 */
public class GUI {

    // Inventory instance representing the GUI
    private final Inventory inv;
    /**
     * Constructor for the GUI class.
     * It initializes the inventory with the GUI inventory from the Data singleton.
     */
    public GUI() {
        this.inv = Data.getInstance().getGUIInventory();
    }

    /**
     * This method opens the GUI for a specific player.
     * @param e HumanEntity object representing the player
     */
    public void openInventory(final HumanEntity e) {
        e.openInventory(this.inv);
    }

    /**
     * This method closes the GUI for a specific player.
     * @param e HumanEntity object representing the player
     */
    public void closeInventory(final HumanEntity e){
        e.closeInventory();
    }


    /**
     * This method initializes the items in the GUI.
     * It adds a skull item for each dead player to the inventory.
     */
    public void initializeItems() {
        // Add the items to the inventory
        HashMap<UUID, Storage> playerList = Data.getInstance().readAllEntries();

        inv.clear();
        for (UUID uuid : playerList.keySet()) {
            if(Data.getInstance().readEntry(uuid).isDead()) {
                inv.addItem(createSkullItem(uuid));
            }
        }
    }

    /**
     * This method creates a skull item for a specific player.
     * @param uuid UUID of the player
     * @return ItemStack object representing the skull item
     */
    public @NotNull ItemStack createSkullItem(UUID uuid) {
        //This is nice --- Chris
        Server server = Data.getInstance().getJavaPlugin().getServer();

        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setOwningPlayer(server.getOfflinePlayer(uuid));
        skull.setItemMeta(meta);

        //...But I like this better --- Chris

//        List<Component> infoLore = new ArrayList<Component>();
//
//        final Component playerName = Component.text(Objects.requireNonNull(server.getOfflinePlayer(uuid).getName()))
//                .color(TextColor.color(0xFFFFFF))
//                .decoration(TextDecoration.BOLD, true);
//
//
//        infoLore.add(playerName);
//
//        //Adds statistics
//        infoLore.addAll(Cost.StatComponent(uuid));
//
//        skull.lore(infoLore);
        return skull;
    }
}
