package org.blackteasea.revival;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class Logic {
//    public static Location newSpawnLocation(Location location) {
//        World world = location.getWorld();
//        Location newLocation = location.clone();
//
//        // Set the Y-coordinate to the sea level
//        int seaLevel = world.getSeaLevel();
//        newLocation.setY(seaLevel);
//
//        // Adjust the Y-coordinate until a passable block is found
//        while (!newLocation.getBlock().isPassable()) {
//            newLocation.add(0, 1, 0);
//        }
//        Block blockAtNewLocation = newLocation.getBlock();
//        while (!blockAtNewLocation.isPassable()) {
//            newLocation.add(0, 1, 0); // Move up if initial location is within a block
//        }
//
//        // Create a Random object
//        Random random = new Random();
//
//        // Randomly choose whether to add or subtract for X and Z coordinates
//        int randomXDirection = random.nextBoolean() ? 1 : -1; // Randomly choose between 1 and -1 for X direction
//        int randomZDirection = random.nextBoolean() ? 1 : -1; // Randomly choose between 1 and -1 for Z direction
//
//        // Keep adding and subtracting until unloaded chunk
//        while (world.isChunkLoaded(newLocation.getBlockX() >> 4, newLocation.getBlockZ() >> 4)) {
//            newLocation.add(randomXDirection * 1000, 0, randomZDirection * 1000);
//        }
//
//        return newLocation;
//    }
}
