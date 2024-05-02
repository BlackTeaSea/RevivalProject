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
    private GUI gui;

    private Data() {
        super(new Object());
        playerList = new ArrayList<>();
        dropEvent = null;
        this.gui = null;
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

    public Inventory getGUIInventory() {
        if (this.inv == null) {
            this.inv = createInventory(null, 9, "Resurrect");
        }
        return this.inv;
    }


    public GUI getGUI() {
        if (this.gui == null) {
            this.gui = new GUI();
        }
        return this.gui;
    }

}
