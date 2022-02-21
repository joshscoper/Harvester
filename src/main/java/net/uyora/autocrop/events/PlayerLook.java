package net.uyora.autocrop.events;

import net.uyora.autocrop.Main;
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
    public void moveEvent(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(player.getEyeLocation().getBlock() instanceof Ageable){
            Block block = player.getEyeLocation().getBlock();
            Ageable ageable = (Ageable) block.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()){
                //TODO spawn hologram of harvest icon
            }
        }
    }
}
