package net.uyora.harvester.menu.inspect;

import net.uyora.harvester.menu.Menu;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InspectMenu extends Menu {

    public InspectMenu(){
        super(null, 9, "Coming Soon");
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }
}
