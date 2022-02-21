package net.uyora.autocrop.events;

import net.uyora.autocrop.Main;
import net.uyora.autocrop.files.PlayerFileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class CropHarvest implements Listener {

    private final Main main;

    public CropHarvest(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler

    public void onHarvest(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location bloc = block.getLocation();
        Ageable ageable = (Ageable) block.getBlockData();
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        if (block.getBlockData() instanceof Ageable) {
            if (ageable.getAge() == ageable.getMaximumAge()) {
                if (main.getConfigManager().getDataConfig().getBoolean("Settings.auto_plant.enabled")) {
                    if (player.hasPermission("Settings.auto_plant.permission")) {
                        if (playerFileManager.getPlayerConfig().getBoolean("auto_plant.enabled")) {
                            event.setCancelled(true);
                            if (player.getInventory().firstEmpty() > -1) {
                                for (ItemStack item : block.getDrops()) {
                                    if (item.getType().toString().contains("SEEDS")) {
                                        item.setType(Material.AIR);
                                    }
                                    player.getInventory().addItem(item);
                                }

                            } else {

                                for (ItemStack item : block.getDrops()) {
                                    if (item.getType().toString().contains("SEEDS")) {
                                        item.setType(Material.AIR);
                                    }
                                    player.getWorld().dropItem(player.getLocation(), item);
                                }
                            }

                            ageable.setAge(1);
                            block.setBlockData(ageable);
                            setBlock(bloc, block);

                        }
                    }
                }
            }
        }
    }


    public void setBlock(Location location, Block block){
        location.getBlock().setType(block.getType());
    }


}
