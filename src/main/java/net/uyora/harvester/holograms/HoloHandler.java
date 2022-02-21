package net.uyora.harvester.holograms;

import dev.geco.gholo.api.GHoloAPI;
import net.uyora.harvester.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HoloHandler {

    private final Main main;
    private final GHoloAPI api;
    private final String hologramName;

    public HoloHandler(Main main){
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

    public void createHologram(Location location, Player player){
        List<String> holoContent = new ArrayList<>();
        String material = main.getConfigManager().getDataConfig().getString("Settings.harvest_hologram.icon.item");
        String modelData = String.valueOf(main.getConfigManager().getDataConfig().getInt("Settings.harvest_hologram.icon.custom_model_data"));
        holoContent.add("ICON:" + material + " {CustomModelData:" + modelData +"}");
        api.insertHolo(hologramName, location, holoContent);
        api.setHoloCondition(hologramName, "%player%=" + player);
        main.getHoloManager().addHolo(location, hologramName);
    }

    public void removeHologram(){
        api.removeHolo(hologramName);
    }




}
