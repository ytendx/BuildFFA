package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class CloseInventory implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().getTitle().equalsIgnoreCase("§eSortiere dein Inventar §8>>")){
            Player p = (Player) e.getPlayer();
            try {
                new InventorySortManager(GameManagement.getConnector()).set(e.getInventory(), p);
            }catch (Exception e1){
                GameManagement.getGame().sCMSG("Fehler in CloseInventory >> " + e1.getMessage());
            }
            p.getInventory().clear();
            GameManagement.setInvItems(p);
        }
    }

}
