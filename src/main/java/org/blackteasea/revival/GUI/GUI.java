package org.blackteasea.revival.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.blackteasea.revival.Storage;
import org.blackteasea.revival.functions.Cost;
import org.blackteasea.revival.Data;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GUI {

    //Basic GUI Things
    private final Inventory inv;
    public GUI() {
        this.inv = Data.getInstance().getGUIInventory();
    }
    public void openInventory(final HumanEntity e) {
        e.openInventory(this.inv);
    }
    public void closeInventory(final HumanEntity e){
        e.closeInventory();
    }



    public void initializeItems() {
        // Add the items to the inventory
        HashMap<UUID, Storage> playerList = Data.getInstance().readAllEntries();

        inv.clear();
        for (UUID uuid : playerList.keySet()) {
            if(!Data.getInstance().readEntry(uuid).isRevived()) {
                Data.getInstance().getJavaPlugin().getServer().getLogger().info("Loading player uuid: " + uuid.toString());
                inv.addItem(createSkullItem(uuid));
            }
        }
    }

    public @NotNull ItemStack createSkullItem(UUID uuid) {
        //This is nice --- Chris
        Server server = Data.getInstance().getJavaPlugin().getServer();

        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setOwningPlayer(server.getOfflinePlayer(uuid));
        skull.setItemMeta(meta);

        //...But I like this better --- Chris

//        List<Component> infolore = new ArrayList<Component>();
//
//        final Component playername = Component.text(Objects.requireNonNull(server.getOfflinePlayer(uuid).getName()))
//                .color(TextColor.color(0xFFFFFF))
//                .decoration(TextDecoration.BOLD, true);
//
//
//        infolore.add(playername);
//
//        //Adds statistics
//        infolore.addAll(Cost.StatComponent(uuid));
//
//        skull.lore(infolore);
        return skull;
    }
}
