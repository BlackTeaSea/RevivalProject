package org.blackteasea.revival;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        inv.clear();
        for (OfflinePlayer player : Data.getInstance().getPlayerList()) {
            inv.addItem(createSkullItem((Player) player));
        }
    }

    public @NotNull ItemStack createSkullItem(Player player) {
        //This is nice --- Chris
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);

        //...But I like this better --- Chris

        List<Component> infolore = new ArrayList<Component>();

        final Component playername = Component.text(player.getName())
                .color(TextColor.color(0xFFFFFF))
                .decoration(TextDecoration.BOLD, true);


        infolore.add(playername);

        //Adds statistics
        infolore.addAll(Cost.StatComponent(player));

        skull.lore(infolore);
        return skull;
    }
}
