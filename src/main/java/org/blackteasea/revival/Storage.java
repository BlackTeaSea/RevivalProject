package org.blackteasea.revival;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("Storage")
public class Storage implements ConfigurationSerializable {
    private Location loc;
    private boolean revived;

    public Storage(Location loc, boolean revived){
        this.loc = loc;
        this.revived = revived;
    }

    public Location getLoc() {
        return loc;
    }
    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public boolean isRevived() {
        return revived;
    }
    public void setRevived(boolean revived) {
        this.revived = revived;
    }

    @Override
    public Map<String, Object> serialize(){
        Map<String, Object> map = new HashMap<>();
        map.put("location", loc);
        map.put("revived", revived);
        return map;
    }

    public static Storage deserialize(Map<String, Object> map){
        return new Storage((Location)map.get("location"), (boolean)map.get("revived"));
    }


}
