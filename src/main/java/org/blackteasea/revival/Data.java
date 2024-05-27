package org.blackteasea.revival;

import org.blackteasea.revival.GUI.CostGUI;
import org.blackteasea.revival.GUI.GUI;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;


import java.beans.PropertyChangeSupport;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.bukkit.Bukkit.createInventory;

public class Data extends PropertyChangeSupport {
    private static Data instance;
    private JavaPlugin plugin;
    private HashMap<UUID, Storage> playerList;
    private Inventory inv;
    private Inventory costinv;
    private GUI gui;
    private CostGUI costgui;
    private Location dropLocation;
    //private Save loader;

    private File storageDocument;
    private FileConfiguration deathListYaml;

    private Data() {
        super(new Object());
        playerList = new HashMap<>();

        this.gui = null;
        dropLocation = null;
        //loader = null;
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

    //Event
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

    //YAML Storage

//    public Writer getWriter(){
//        Writer writer;
//        try{
//            writer = new FileWriter("./plugins/storage.yaml");
//        } catch (Exception e){
//            System.out.println("No");
//            return null;
//        }
//        return writer;
//
//    }
//    public InputStream getIO(){
//        InputStream io;
//        try{
//            io = Files.newInputStream(Paths.get(storageDocument.getName()));
//        } catch (Exception e){
//            System.out.println("?No.");
//            return null;
//        }
//        return io;
//    }

    public void createEntry(UUID uuid, Location loc, boolean revived){
        if(playerList.containsKey(uuid)){
            return;
        }
        playerList.put(uuid, new Storage(loc, revived));
    }
    public Storage readEntry(UUID uuid){
        return playerList.getOrDefault(uuid, new Storage(null, false));
    }
    public void updateEntry(UUID uuid, Storage store){
        playerList.replace(uuid, store);
    }
    public void deleteEntry(UUID uuid){
        playerList.remove(uuid);
    }
    public boolean checkEntry(UUID uuid){
        return playerList.containsKey(uuid);
    }

//    public void save() {
//        Yaml yaml = new Yaml();
//        Map<String, Boolean> copy = new HashMap<>();
//        for (UUID uuid : playerList.keySet()) {
//            copy.put(uuid.toString(), copy.get(uuid));
//        }
//        try (Writer writer = new FileWriter("./plugins/storage.yaml")) {
//            yaml.dump(playerList, writer);
//        } catch (IOException e) {
//            plugin.getLogger().severe("Failed to save data: " + e.getMessage());
//        }
//    }
//    public void load(String filepath) {
//        Yaml yaml = new Yaml();
//        this.storageDocument = new File(filepath);
//        try {
//            if (!storageDocument.exists() && !storageDocument.createNewFile()) {
//                plugin.getLogger().warning("Could not create new storage file.");
//            } else {
//                try (InputStream io = Files.newInputStream(storageDocument.toPath())) {
//                    Map<String, Storage> copy = yaml.load(io);
//                    if (copy != null) {
//                        for (Map.Entry<String, Storage> entry : copy.entrySet()) {
//                            playerList.put(UUID.fromString(entry.getKey()), entry.getValue());
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            plugin.getLogger().severe("Failed to load data: " + e.getMessage());
//        }
//    }

    public HashMap<UUID, Storage> readAllEntries(){
        return playerList;
    }

    public void testPrintEntries(){
        for(UUID uuid : playerList.keySet()){
            getJavaPlugin().getServer().getLogger().info(uuid.toString());
        }
    }

    public void deathListLoad(){
        deathListYaml = new YamlConfiguration();
        try{
            deathListYaml.load(new File("./plugins/deathlist.yml"));
        } catch (Exception e){
            e.printStackTrace();
        }

        for (String uuid : deathListYaml.getKeys(false)){
            getJavaPlugin().getServer().getLogger().info("HERE IS SOME LOUD THING FOR STUFF!!! UUID of current read: " + uuid);
            playerList.put(UUID.fromString(uuid), (Storage) deathListYaml.get(uuid));
        }
    }

    public void deathListSave(){
        deathListYaml = new YamlConfiguration();

        for(UUID uuid : playerList.keySet()){
            deathListYaml.set(uuid.toString(), playerList.get(uuid));
        }
        try{
            deathListYaml.save(new File("./plugins/deathlist.yml"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
