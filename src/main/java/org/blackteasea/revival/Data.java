package org.blackteasea.revival;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.createInventory;

public class Data extends PropertyChangeSupport {
    private static Data instance;
    private JavaPlugin plugin;

    private PlayerDropItemEvent dropEvent;
    private final List<Player> playerList;

    private Inventory inv;

    private Data() {
        super(new Object());
        playerList = new ArrayList<>();
        dropEvent = null;
        inv = createInventory(null, 9, "Resurrect");
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public JavaPlugin getJavaPlugin() {
        return plugin;
    }

    public void setJavaPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    public void removePlayer(Player player) {
        this.playerList.remove(player);
    }

    public List<Player> getPlayerList() {
        return this.playerList;
    }

    public PlayerDropItemEvent getDropEvent() {
        return this.dropEvent;
    }

    public void setDropEvent(PlayerDropItemEvent event) {
        this.dropEvent = event;
    }

    public void initializeItems(Inventory inv) {
        // Add the items to the inventory
        for (Player player : Data.getInstance().getPlayerList()) {
            inv.addItem(Inv.createSkullItem(player, player.getName()));
        }
    }

    public Inventory getInventory() {
        return this.inv;
    }

    public void setInventory(Inventory inv) {
        this.inv = inv;
    }

}
