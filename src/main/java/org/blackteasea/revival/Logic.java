package org.blackteasea.revival;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Logic {
    public static Location newSpawnLocation(Location location){
        Location newLocation = location.clone();
        double scale = location.distanceSquared(new Location(location.getWorld(), 0, 0, 0));
        newLocation = newLocation.multiply(scale/2);
        while (location.getWorld().getBlockAt(newLocation).getType().isSolid()) {
            int highestY = location.getWorld().getHighestBlockYAt(newLocation);
            newLocation.setY(highestY + 1);
        }
        if (location.getWorld().isChunkLoaded(newLocation.getBlockX() >> 4, newLocation.getBlockZ() >> 4)) {
            newLocation.add(1000, 0, 1000);
        }
        return newLocation;
    }
}
