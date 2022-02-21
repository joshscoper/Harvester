package net.uyora.autocrop.events;

import net.uyora.autocrop.Main;
import net.uyora.autocrop.files.PlayerFileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InspectCrop implements Listener {

    private final Main main;

    public InspectCrop(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        if (main.getConfigManager().getDataConfig().getBoolean("Settings.inspect.enable")){
            if (playerFileManager.getPlayerConfig().getBoolean("inspect_plant.enabled")) {
                if (action.equals(Action.RIGHT_CLICK_BLOCK) && player.isSneaking()) {
                    Ageable ageable = (Ageable) block.getBlockData();
                    int age = ageable.getAge();
                    int maxAge = ageable.getMaximumAge();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&lCrop Inspect &f>>> &aGrowth Stage &a" + age + "&7 / &a" + maxAge));
                }
            }
        }
    }

}
