package org.blackteasea.revival;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class GUI {
    private final Inventory inv;

    public GUI() {
        this.inv = Data.getInstance().getInventory();
    }
    public void openInventory(final HumanEntity e) {
        e.openInventory(this.inv);
    }
    public void closeInventory(final HumanEntity e){
        e.closeInventory();
    }

    public void initializeItems() {
        // Add the items to the inventory
        for (Player player : Data.getInstance().getPlayerList()) {
            inv.addItem(createSkullItem(player, player.getName()));
        }
    }

    public @NotNull ItemStack createSkullItem(Player player, final String... lore) {
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);
        skull.setLore(Arrays.asList(lore));
        return skull;
    }
}
