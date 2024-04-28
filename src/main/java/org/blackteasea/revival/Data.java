package org.blackteasea.revival;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

public class Data extends PropertyChangeSupport {
    private static Data instance;
    private JavaPlugin plugin;
    private Data() {
        super(new Object());
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
}
