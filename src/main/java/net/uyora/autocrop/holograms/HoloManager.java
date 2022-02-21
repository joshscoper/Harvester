package net.uyora.autocrop.holograms;

import dev.geco.gholo.api.GHoloAPI;
import net.uyora.autocrop.Main;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HoloManager {

    private final Main main;
    private GHoloAPI api;
    private String hologramName;

    public HoloManager(Main main){
        this.main = main;
        api = new GHoloAPI();
        hologramName = generateUUID();
    }

    //Generates random uuid for hologram name
    public String generateUUID(){
        String uuid;
        UUID id = UUID.randomUUID();
        uuid = String.valueOf(id);
        return uuid;
    }

    public void createHologram(Location location){
        List<String> holoContent = new ArrayList<String>();
        String material = main.getConfigManager().getDataConfig().getString("harvest_hologram.icon.item");
        String modelData = String.valueOf(main.getConfigManager().getDataConfig().getInt("harvest_hologram.icon.custom_model_data"));
        holoContent.add("ICON:" + material.toUpperCase() + " {CustomModelData:" + modelData +"}");
        api.insertHolo(hologramName, location, holoContent);
    }

    public void removeHologram(){
        api.removeHolo(hologramName);
    }



}
