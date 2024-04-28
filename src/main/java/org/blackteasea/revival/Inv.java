package org.blackteasea.revival;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Inv {
    public static void openInventory(final HumanEntity e) {
        Inventory inv = Data.getInstance().getInventory();
        e.openInventory(inv);
    }
    public static void closeInventory(final HumanEntity e){
        e.closeInventory();
    }

    //Skullwork

    public static @NotNull ItemStack createSkullItem(Player player, final String... lore) {
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);
        skull.setLore(Arrays.asList(lore));
        return skull;
    }
}
