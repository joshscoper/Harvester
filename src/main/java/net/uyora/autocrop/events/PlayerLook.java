package net.uyora.autocrop.events;

import net.uyora.autocrop.Main;
import net.uyora.autocrop.files.PlayerFileManager;
import net.uyora.autocrop.holograms.HoloManager;
import org.bukkit.FluidCollisionMode;
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
    }

    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getTargetBlockExact(3, FluidCollisionMode.ALWAYS);
        Location hologramLocation = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ());
        HoloManager holoManager = new HoloManager(main);
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        if (main.getConfigManager().getDataConfig().getBoolean("Settings.harvest_hologram.enabled")) {
            if (playerFileManager.getPlayerConfig().getBoolean("harvest_hologram.enabled")) {
                if (main.hologramEnabled()) {
                    if (block.getBlockData() instanceof Ageable) {
                        Ageable ageable = (Ageable) block.getBlockData();
                        if (ageable.getAge() == ageable.getMaximumAge()) {
                            holoManager.createHologram(hologramLocation);
                        }
                        holoManager.removeHologram();
                    }
                }
            }
        }
    }
}
