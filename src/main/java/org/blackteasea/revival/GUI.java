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
        for (Player player : Data.getInstance().getPlayerList()) {
            inv.addItem(createSkullItem(
                    player,
                    player.getName(),
                    Cost.displayDeathCount(player),
                    Cost.displayDeathTime(player)
                    ));
        }
    }
    public void deinitializeItems() {
        this.inv.clear();
    }

    public @NotNull ItemStack createSkullItem(Player player, final String... lore) {
        //This is nice --- Chris
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);

        //...But I like this better --- Chris
        skull.setLore(Arrays.asList(lore));
        return skull;
    }
}
