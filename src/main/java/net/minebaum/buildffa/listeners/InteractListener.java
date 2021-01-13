package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent e){
        final Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getItemMeta().getDisplayName().equals("§eKits und Gadgets")){
                GameManagement.getKitManager().openMenuInv(p);
            }
            if(e.getItem().getItemMeta().getDisplayName().equals("§cNavigator")){
                p.openInventory(SpecHandler.navInv());
            }
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cInventarsortierung")){
                new InventorySortManager(GameManagement.getConnector()).openSetInventory(p);
                p.sendMessage(Data.PREFIX + "§cSortiere dein Inventar!");
            }
        }
    }

}
