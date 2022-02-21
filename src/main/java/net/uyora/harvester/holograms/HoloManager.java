package net.uyora.harvester.holograms;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;


public class HoloManager {

    private final Map<Location, String> holoMap;

    public HoloManager(){
        holoMap = new HashMap<>();
    }

    public Boolean holoExists(Location location){
        return holoMap.containsKey(location);
    }

    public void addHolo(Location location, String uuid){
        holoMap.put(location,uuid);
    }

    public void removeHolo(Location location){
        holoMap.remove(location);
    }

    public void clearHoloMap(){
        holoMap.clear();
    }

    public Map<Location, String> getHoloMap(){return holoMap;}

}
