package org.blackteasea.revival;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.createInventory;

public class Data extends PropertyChangeSupport {
    private static Data instance;
    private JavaPlugin plugin;
    private PlayerDropItemEvent dropEvent;
    private HashMap<UUID, Boolean> playerList;
    private Inventory inv;
    private Inventory costinv;
    private GUI gui;
    private CostGUI costgui;
    private Location dropLocation;
    private Save loader;

    private Data() {
        super(new Object());
        playerList = new HashMap<>();

        dropEvent = null;
        this.gui = null;
        dropLocation = null;
        loader = null;
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
    public void addPlayer(UUID playerUUID) {
        this.playerList.put(playerUUID, true);
    }
    public void removePlayer(UUID playerUUID) {
        this.playerList.remove(playerUUID);
    }
    public HashMap<UUID, Boolean> getPlayerList() {
        return this.playerList;
    }
    public void setPlayerList(HashMap<UUID, Boolean> players) {
        this.playerList = players;
    }
    public PlayerDropItemEvent getDropEvent() {
        return this.dropEvent;
    }
    public void setDropEvent(PlayerDropItemEvent event) {
        this.dropEvent = event;
    }
    public Inventory getGUIInventory() {
        if (this.inv == null) {
            this.inv = createInventory(null, 9, "Resurrect");
        }
        return this.inv;
    }
    public Inventory getLargeGUIInventory() {
        if (this.costinv == null) {
            this.costinv = createInventory(null, 54, "Cost");
        }
        return this.costinv;
    }
    public void setLargeGUIInventory(Inventory inv) {
        this.costinv = inv;
    }
    public GUI getGUI() {
        if (this.gui == null) {
            this.gui = new GUI();
        }
        return this.gui;
    }
    public CostGUI getCostGUI() {
        if (this.costgui == null) {
            this.costgui = new CostGUI();
        }
        return this.costgui;
    }
    public void setDropLocation(Location location) {
        this.dropLocation = location;
    }
    public Location getDropLocation() {
        return this.dropLocation;
    }
    public Save getLoader(){
        return this.loader;
    }
    public void setLoader(Save loader) {
        this.loader = loader;
    }

}
