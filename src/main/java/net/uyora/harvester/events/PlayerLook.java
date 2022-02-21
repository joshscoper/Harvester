package net.uyora.harvester.events;

import net.uyora.harvester.Main;
import net.uyora.harvester.files.PlayerFileManager;
import net.uyora.harvester.holograms.HoloHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerLook implements Listener {

    private final Main main;

    public PlayerLook(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void playerLook(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Block block = player.getTargetBlock(null, 5);
        PlayerFileManager fileManager = new PlayerFileManager(main, player);
        HoloHandler holoHandler = new HoloHandler(main);
        Location holoLoc = new Location(block.getWorld(),block.getX(),block.getY() + 1 ,block.getZ());
        long duration = main.getConfigManager().getDataConfig().getLong("Settings.harvest_hologram.duration");
        if (block.getBlockData() instanceof Ageable) {
            Ageable ageable = (Ageable) block.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()) {
                if (main.getConfigManager().getDataConfig().getBoolean("Settings.harvest_hologram.enabled")) {
                    if (fileManager.getPlayerConfig().getBoolean("harvest_hologram")) {
                        if (!main.getHoloManager().holoExists(holoLoc)) {
                            holoHandler.createHologram(holoLoc, player);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                                holoHandler.removeHologram();
                                main.getHoloManager().removeHolo(holoLoc);
                            }, duration);
                        }
                    }
                }
            }
        }
    }
}
