package net.uyora.autocrop.events;

import net.uyora.autocrop.Main;
import net.uyora.autocrop.files.PlayerFileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private final Main main;

    public Join (Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler

    public void onJoin(PlayerJoinEvent event){
        PlayerFileManager playerFileManager = new PlayerFileManager(main, event.getPlayer());
        playerFileManager.createFile();
    }



}
