package org.blackteasea.revival;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public class CostGUI {
    //Initializes the inventory
    private final Inventory inv;

    //Constructs the CostGUI object
    public CostGUI() {this.inv = Data.getInstance().getLargeGUIInventory();}
    public void openInventory(HumanEntity e) {e.openInventory(this.inv);}
    public void closeInventory(HumanEntity e) {e.closeInventory();}


}
