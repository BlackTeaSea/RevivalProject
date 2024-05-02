package org.blackteasea.revival;

import org.bukkit.Location;

public class Logic {
    public static Location newSpawnLocation(Location location){
        Location newLocation = location.clone();
        double scale = location.distanceSquared(new Location(location.getWorld(), 0, 0, 0));
        newLocation = newLocation.multiply(scale/2);
        newLocation.setY(location.getWorld().getSeaLevel());
        while (!newLocation.getBlock().isPassable()) {
            newLocation.add(0, 1, 0);
        }
        if (location.getWorld().isChunkLoaded(newLocation.getBlockX() >> 4, newLocation.getBlockZ() >> 4)) {
            newLocation.add(1000, 0, 1000);
        }
        return newLocation;
    }
}
