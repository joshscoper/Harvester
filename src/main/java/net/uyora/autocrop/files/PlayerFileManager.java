package net.uyora.autocrop.files;

import net.uyora.autocrop.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerFileManager {

    private Player player;
    private final Main main;
    private File playerFile;
    private File playerData;
    private YamlConfiguration playerConfig;


    public PlayerFileManager(Main main, Player player) {
        this.main = main;
        this.player = player;
        this.playerFile = new File(main.getDataFolder() + File.separator + "PlayerData");
        this.playerData = new File(playerFile, player.getUniqueId().toString() + ".yml");
        this.playerConfig = YamlConfiguration.loadConfiguration(playerData);
    }

    public void createFile() {
        //Creates Directory
        if (!playerFile.exists()){
            playerFile.mkdirs();
        }
        //Creates Player Files
        if (!playerData.exists()){
            try {
                playerData.createNewFile();
                playerConfig.createSection("auto_plant.enabled");
                playerConfig.createSection("inspect_plant.enabled");
                playerConfig.set("auto_plant.enabled", true);
                playerConfig.set("inspect_plant.enabled",true);
                playerConfig.save(playerData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Save File Manually
    public void saveFile(){
        try{
            playerConfig.save(playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //File Check
    public boolean hasFile(){
        if (playerData.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void setPath(String path, Object value){
        playerConfig.set(path,value);
        saveFile();
    }

    public YamlConfiguration getPlayerConfig(){return playerConfig;}

}

