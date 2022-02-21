package net.uyora.autocrop.events;

import net.uyora.autocrop.Main;
import net.uyora.autocrop.files.PlayerFileManager;
import org.bukkit.CropState;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

public class CropHarvest implements Listener {

    private final Main main;

    public CropHarvest(Main main){
        this.main = main;
    }

    @EventHandler

    public void onHarvest(PlayerHarvestBlockEvent event){
        Player player = event.getPlayer();
        Block block = event.getHarvestedBlock();
        if (block instanceof Crops) {
            Crops crop = (Crops) block.getBlockData();
            PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
            if (crop.getState().equals(CropState.RIPE)) {
                if (main.getConfigManager().getDataConfig().getBoolean("Settings.auto_plant.enabled")) {
                    if (player.hasPermission(main.getConfigManager().getDataConfig().getString("Settings.auto_plant.permission"))) {
                        if (playerFileManager.getDataConfig().getBoolean("auto_plant.enabled")) {
                            for (ItemStack item : event.getItemsHarvested()) {
                                if (item.getType().name().contains("SEEDS")) {
                                    event.getItemsHarvested().remove(event.getItemsHarvested().indexOf(item));
                                    crop.setState(CropState.SEEDED);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
