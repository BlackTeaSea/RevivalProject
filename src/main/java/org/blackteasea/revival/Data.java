package org.blackteasea.revival;

import org.blackteasea.revival.GUIs.GUI;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.*;
import static org.bukkit.Bukkit.createInventory;

/**
 * This class is a singleton that manages data related to the game.
 * It extends PropertyChangeSupport to allow other classes to listen for changes to its properties.
 */
public class Data extends PropertyChangeSupport {
    // Singleton instance of the Data class
    private static Data instance;

    // Instance of the JavaPlugin
    private JavaPlugin plugin;

    // Map of player UUIDs to their Storage objects
    private final HashMap<UUID, Storage> playerList;

    // Inventory for the GUI
    private Inventory inv;

    // Instance of the GUI
    private GUI gui;

    // Location where the drop occurred
    private Location dropLocation;

    // Configuration for the death list
    private FileConfiguration deathListYaml;

    /**
     * Private constructor for the Data class.
     * It initializes the playerList and sets gui and dropLocation to null.
     */
    private Data() {
        super(new Object());
        playerList = new HashMap<>();

        this.gui = null;
        dropLocation = null;
        //loader = null;
    }

    /**
     * This method returns the singleton instance of the Data class.
     * If the instance is null, it creates a new instance.
     *
     * @return Data object representing the singleton instance
     */
    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    /**
     * This method returns the JavaPlugin instance.
     *
     * @return JavaPlugin object representing the plugin
     */
    public JavaPlugin getJavaPlugin() {
        return plugin;
    }

    /**
     * This method sets the JavaPlugin instance.
     *
     * @param plugin JavaPlugin object representing the plugin
     */
    public void setJavaPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * This method returns the Inventory for the GUI.
     * If the inventory is null, it creates a new inventory.
     *
     * @return Inventory object representing the GUI inventory
     */
    public Inventory getGUIInventory() {
        if (this.inv == null) {
            this.inv = createInventory(null, 9, "Resurrect");
        }
        return this.inv;
    }

    /**
     * This method returns the GUI instance.
     * If the GUI is null, it creates a new GUI.
     *
     * @return GUI object representing the GUI
     */
    public GUI getGUI() {
        if (this.gui == null) {
            this.gui = new GUI();
        }
        return this.gui;
    }

    /**
     * This method sets the drop location.
     *
     * @param location Location object representing the drop location
     */
    public void setDropLocation(Location location) {
        this.dropLocation = location;
    }

    /**
     * This method returns the drop location.
     *
     * @return Location object representing the drop location
     */
    public Location getDropLocation() {
        return this.dropLocation;
    }

    /**
     * This method creates a new Storage object for the player and adds it to the playerList.
     *
     * @param uuid UUID object representing the player
     * @param loc Location object representing the player's location
     * @param revived boolean representing if the player was revived
     */
    public void createEntry(UUID uuid, Location loc, boolean revived){
        if(playerList.containsKey(uuid)){
            return;
        }
        playerList.put(uuid, new Storage(loc, revived));
    }

    /**
     * This method reads the Storage object for the player from the playerList.
     *
     * @param uuid UUID object representing the player
     * @return Storage object representing the player's storage
     */
    public Storage readEntry(UUID uuid){
        return playerList.getOrDefault(uuid, new Storage(null, false));
    }

    /**
     * This method updates an entry in the playerList.
     *
     * @param uuid UUID of the player
     * @param store Storage object representing the player's data
     */
    public void updateEntry(UUID uuid, Storage store){
        playerList.replace(uuid, store);
    }

    /**
     * This method deletes an entry from the playerList.
     *
     * @param uuid UUID of the player
     */
    public void deleteEntry(UUID uuid){
        playerList.remove(uuid);
    }

    /**
     * This method checks if an entry exists in the playerList.
     *
     * @param uuid UUID of the player
     * @return boolean representing if the entry exists
     */
    public boolean checkEntry(UUID uuid){
        return playerList.containsKey(uuid);
    }

    /**
     * This method returns the playerList.
     *
     * @return HashMap of UUIDs to Storage objects
     */
    public HashMap<UUID, Storage> readAllEntries(){
        return playerList;
    }

    /**
     * This method loads the death list from a YAML file.
     * If an error occurs, it logs the error message.
     */
    public void load(){
        deathListYaml = new YamlConfiguration();
        try{
            deathListYaml.load(new File("./plugins/deathList.yml"));
        } catch (Exception e){
            this.plugin.getLogger().info(e.getMessage());
        }

        for (String uuid : deathListYaml.getKeys(false)){
            getJavaPlugin().getLogger().info("HERE IS SOME LOUD THING FOR STUFF!!! UUID of current read: " + uuid);
            playerList.put(UUID.fromString(uuid), (Storage) deathListYaml.get(uuid));
        }
    }

    /**
     * This method saves the death list to a YAML file.
     * If an error occurs, it logs the error message.
     */
    public void save(){
        deathListYaml = new YamlConfiguration();

        for(UUID uuid : playerList.keySet()){
            deathListYaml.set(uuid.toString(), playerList.get(uuid));
        }
        try{
            deathListYaml.save(new File("./plugins/deathList.yml"));
        } catch (Exception e){
            this.plugin.getLogger().info(e.getMessage());
        }

    }
}
