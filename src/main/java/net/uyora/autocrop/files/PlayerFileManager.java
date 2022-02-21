package net.uyora.autocrop.files;

import net.uyora.autocrop.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class PlayerFileManager {
    private final Main main;
    private Player player;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public PlayerFileManager(Main main, Player player) {
        this.main = main;
        saveDefaultConfig();
    }

    public void reloadDataConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.main.getDataFolder(), player.getUniqueId() + ".yml");
            this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
            this.dataConfig.createSection("auto_plant.enabled");
            this.dataConfig.set("auto_plant.enabled", true);
        }
    }

    public void setDataConfig(String path, String value) {
        if (this.dataConfig == null || this.configFile == null) {
            return;
        }
        this.dataConfig.set(path, value);
        saveDataConfig();
    }

    public FileConfiguration getDataConfig() {
        if (this.dataConfig == null) {
            reloadDataConfig();
        }
        return this.dataConfig;
    }


    public void saveDataConfig() {
        if (this.dataConfig == null || this.configFile == null) {
            return;
        }
        try {
            this.getDataConfig().save(this.configFile);
        } catch (IOException e) {
            this.main.getLogger().log(Level.SEVERE, "Error Saving" + player.getUniqueId() + ".yml", e);
        }
    }

    public void saveDefaultConfig() {
        if (getDataConfig() == null) {
            reloadDataConfig();
        }

        if (!this.configFile.exists()) {
            this.main.saveResource(player.getUniqueId() + ".yml", false);
        }

    }
}