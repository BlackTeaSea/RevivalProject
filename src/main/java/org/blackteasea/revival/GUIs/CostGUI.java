package org.blackteasea.revival.GUIs;

import org.blackteasea.revival.Data;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CostGUI {
    //Initializes the inventory
    private final Inventory inv;

    //Constructs the CostGUI object
    public CostGUI() {this.inv = Data.getInstance().getLargeGUIInventory();}
    public void openInventory(HumanEntity e) {e.openInventory(this.inv);}
    public void closeInventory(HumanEntity e) {e.closeInventory();}

    public void initializeItems() {
        // Add the items to the inventory
        inv.clear();
        inv.setItem(46, new ItemStack(Material.RED_STAINED_GLASS, 1));
        inv.setItem(54, new ItemStack(Material.LIME_STAINED_GLASS, 1));
    }
}
