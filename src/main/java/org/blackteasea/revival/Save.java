package org.blackteasea.revival;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Save implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286330L;

    public final HashMap<UUID, Boolean> playerList;
    public Save(HashMap<UUID, Boolean> playerList){
        this.playerList = playerList;
    }

    public Save(Save save) {
        this.playerList = save.playerList;
    }

    public boolean saveData(String filePath) {
        try {
            // Check if the REVIVAL folder exists, if not, create it
            File folder = new File(filePath).getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            System.out.println(playerList.toString());

            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(Files.newOutputStream(Paths.get(filePath))));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Save loadData(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // If the file doesn't exist, create a new Save object with default values
                Save defaultSave = new Save(Data.getInstance().getPlayerList());
                defaultSave.saveData(filePath); // Save the default data to the file
                return defaultSave;
            } else {
                // Load data from the file
                BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
                Save save = (Save) in.readObject();
                in.close();
                return save;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSave(){
        HashMap<UUID, Boolean> playerList = Data.getInstance().getPlayerList();

        new Save(playerList).saveData("./plugins/Revival/Revival.dat");
        Data.getInstance().getJavaPlugin().getLogger().info("Data saved");
    }

    public Save getSave(){

        Save save = new Save(Objects.requireNonNull(Save.loadData("./plugins/Revival/Revival.dat")));
        Data.getInstance().setPlayerList(save.playerList);
        Data.getInstance().getJavaPlugin().getLogger().info("Data loaded");
        return save;
    }


}