package org.blackteasea.revival;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Save implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286330L;

    public final List<Player> playerList;
    public final Inventory inv;
    public final Location dropLocation;
    public Save(List<Player> playerList, Inventory inv, Location dropLocation){
        this.inv = inv;
        this.dropLocation = dropLocation;
        this.playerList = playerList;
    }

    public Save(Save save) {
        this.inv = save.inv;
        this.dropLocation = save.dropLocation;
        this.playerList = save.playerList;
    }

    public boolean saveData(String filePath) {
        try {
            // Check if the REVIVAL folder exists, if not, create it
            File folder = new File(filePath).getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
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
                Save defaultSave = new Save(new ArrayList<>(), null, null);
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
        List<Player> playerList = Data.getInstance().getPlayerList();
        Inventory inv = Data.getInstance().getLargeGUIInventory();
        Location dropLocation = Data.getInstance().getDropLocation();
        new Save(playerList, inv, dropLocation).saveData("./plugins/Revival/Revival.dat");
        Data.getInstance().getJavaPlugin().getLogger().info("Data saved");
    }

    public void getSave(){
        Save data = new Save(Objects.requireNonNull(Save.loadData("./plugins/Revival/Revival.dat")));
        Data.getInstance().setPlayerList(playerList);
        Data.getInstance().setLargeGUIInventory(data.inv);
        Data.getInstance().setDropLocation(data.dropLocation);
        Data.getInstance().getJavaPlugin().getLogger().info("Data loaded");
    }


}