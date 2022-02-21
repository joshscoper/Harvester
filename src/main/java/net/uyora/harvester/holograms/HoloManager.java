package net.uyora.harvester.holograms;

import net.uyora.harvester.Main;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;


public class HoloManager {

    private final Main main;
    private final Map<Location, String> holoMap;

    public HoloManager(Main main){
        this.main = main;
        holoMap = new HashMap<Location,String>();
    }

    public Boolean holoExists(Location location){
        if (holoMap.containsKey(location)){
            return true;
        }
        return false;
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

    public String getHologram(Location location){
        return holoMap.get(location);
    }

    public Map<Location, String> getHoloMap(){return holoMap;}

}
