package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.GameManagement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§eKits und Gadgests")){
            GameManagement.getKitManager().openMenuInv(p);
        }
    }

}
