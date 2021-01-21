package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.mysql.SQLStats;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cEinstellungen")){
                p.openInventory(SpecHandler.confInv(p));
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSpectatormodus Verlassen")){
                Bukkit.dispatchCommand(p, "spec");
            }
            if(e.getItem().getItemMeta().getDisplayName().contains("§cStats")){
                SQLStats.openStatsHead(p);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_GUITAR, 1, 1);
            }
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück zur Lobby §8■▶")){
                p.kickPlayer(Data.PREFIX + "§eDu wurdest auf eine Lobby gesendet...");
            }
        }
    }

}
