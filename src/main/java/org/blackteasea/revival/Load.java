package org.blackteasea.revival;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Load {

    private final String dataFileName = "player_data.dat";

    public void savePlayerData(List<Player> players) {
        try {
            FileOutputStream fileOut = new FileOutputStream(dataFileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new ArrayList<>(players));
            out.close();
            fileOut.close();
            System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Player> loadPlayerData() {
        List<Player> players = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(dataFileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<String> playerNames = (List<String>) in.readObject();
            in.close();
            fileIn.close();

            // Convert player names to Player objects
            for (String playerName : playerNames) {
                Player player = Bukkit.getPlayerExact(playerName);
                if (player != null) {
                    players.add(player);
                }
            }
            System.out.println("Player data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return players;
    }
}
