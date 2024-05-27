package org.blackteasea.revival.Experimental;

import org.blackteasea.revival.Storage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestingYaml {
    FileConfiguration deathListYaml;
    Map<UUID, Storage> deathListMap = new HashMap<>();



    public void deathListLoad(){
        deathListYaml = new YamlConfiguration();
        try{
            deathListYaml.save(new File("deathlist.yml"));
        } catch (Exception e){
            e.printStackTrace();
        }

        for (String uuid : deathListYaml.getKeys(false)){
            deathListMap.put(UUID.fromString(uuid), (Storage) deathListYaml.get(uuid));
        }
    }

    public void deathListSave(Map<UUID, Storage> map){
        deathListYaml = new YamlConfiguration();

        for(UUID uuid : map.keySet()){
            deathListYaml.set(uuid.toString(), map.get(uuid));
        }
        try{
            deathListYaml.save(new File("deathlist.yml"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
