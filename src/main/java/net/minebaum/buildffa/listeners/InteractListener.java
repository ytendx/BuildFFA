package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @SuppressWarnings ("deprecation")
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem().getItemMeta().getDisplayName().equals("§eKits und Gadgets")){
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                GameManagement.getKitManager().openMenuInv(p);
            }
        }
        if(e.getItem().getItemMeta().getDisplayName().equals("§cNavigator")){
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                p.openInventory(SpecHandler.navInv());
            }
        }
    }

}
