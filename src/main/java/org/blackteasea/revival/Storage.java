package org.blackteasea.revival;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is annotated with @SerializableAs("Storage") to indicate that it is serializable.
 * It implements the ConfigurationSerializable interface to allow for serialization of its state.
 */
@SerializableAs("Storage")
public class Storage implements ConfigurationSerializable {
    private final Location loc;
    private final boolean revived;

    /**
     * Constructor for the Storage class.
     * @param loc Location object representing a specific location
     * @param revived Boolean value indicating whether the Player has been revived
     */
    public Storage(Location loc, boolean revived){
        this.loc = loc;
        this.revived = revived;
    }

    /**
     * Getter for the Location object.
     * @return Location object representing a specific location
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * Method to check if the Player is dead.
     * @return Boolean value indicating whether the Player is dead
     */
    public boolean isDead() {
        return !revived;
    }
    /**
     * Method to serialize the state of the Player into a Map.
     * @return Map containing the serialized state of the Player
     */
    @Override
    public @NotNull Map<String, Object> serialize(){
        Map<String, Object> map = new HashMap<>();
        map.put("location", loc);
        map.put("revived", revived);
        return map;
    }
    /**
     * Static method to deserialize a Map into a Storage object.
     * @param map Map containing the serialized state of the Player
     * @return Storage object created from the deserialized state
     */
    public static Storage deserialize(Map<String, Object> map){
        return new Storage((Location)map.get("location"), (boolean)map.get("revived"));
    }

}
