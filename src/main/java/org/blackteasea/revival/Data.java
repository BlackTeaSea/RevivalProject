package org.blackteasea.revival;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Data extends PropertyChangeSupport {
    private static Data instance;
    private JavaPlugin plugin;

    private List<Player> playerList;
    private Data() {
        super(new Object());
        playerList = new ArrayList<>();
    }

    public static Data getInstance(){
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }

    public void setJavaPlugin(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public JavaPlugin getJavaPlugin() {
        return plugin;
    }

    public void addPlayer(Player player){
        this.playerList.add(player);
    }

    public void removePlayer(Player player){
        this.playerList.remove(player);
    }

    public List<Player> getPlayerList(){
        return this.playerList;
    }
}
