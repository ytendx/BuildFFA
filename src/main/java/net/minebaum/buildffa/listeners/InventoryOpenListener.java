package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryOpenListener implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent e){
        if(e.getInventory().getType().equals(InventoryType.ENDER_CHEST)){
            e.setCancelled(true);
            e.getPlayer().closeInventory();
            for(Player all : Bukkit.getOnlinePlayers()){
                if(all.hasPermission("system.spec")){
                    all.sendMessage(Data.PREFIX + "§cDer Spieler " + e.getPlayer().getName() + " versucht seine Enderchest zu nutzen! (Bugusing)");
                }
            }
        }
    }

}
