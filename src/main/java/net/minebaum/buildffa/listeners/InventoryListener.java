package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        if(p.getLocation().getY() >= 90){
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eKits")){
                p.sendMessage("Jetzt würden sich Kits öffnen!"); //TODO
                p.sendMessage(Data.PREFIX + "§cWähle ein Kit!");
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eGadgets")){
                p.sendMessage("Jetzt würden sich Gadgets öffnen!"); //TODO
                p.sendMessage(Data.PREFIX + "§cWähle ein Gadget!");
            }
        }
    }

}
