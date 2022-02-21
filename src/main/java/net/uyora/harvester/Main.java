package net.uyora.harvester;

import dev.geco.gholo.api.GHoloAPI;
import net.uyora.harvester.events.CropHarvest;
import net.uyora.harvester.events.Join;
import net.uyora.harvester.events.PlayerLook;
import net.uyora.harvester.files.ConfigManager;
import net.uyora.harvester.holograms.HoloManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private ConfigManager configManager;
    private HoloManager holoManager;

    @Override
    public void onEnable() {
        initializeClasses();
        registerEvents();

    }

    @Override
    public void onDisable() {
        if (hologramEnabled()){
            removeAllHolograms();
        }
    }

    public void initializeClasses(){
        configManager = new ConfigManager(this);
        holoManager = new HoloManager();
    }

    public void registerEvents(){
        new CropHarvest(this);
        new Join(this);
        new PlayerLook(this);
    }

    public Boolean hologramEnabled(){
        if (Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("GHolo")).isEnabled()){
            return true;
        } else {
            return false;
        }
    }

    public void removeAllHolograms(){
        for (String uuid : getHoloManager().getHoloMap().values()){
            GHoloAPI api = new GHoloAPI();
            api.removeHolo(uuid);
        }
        holoManager.clearHoloMap();
    }

    public ConfigManager getConfigManager(){return configManager;}
    public HoloManager getHoloManager(){return holoManager;}


}
