package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.GagetsManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§cOnlinespieler")){
            e.setCancelled(true);
            Player target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
            if(target != null){
                p.teleport(target);
                p.closeInventory();
            }
        }
        if(p.getLocation().getY() >= 195){
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eKits")){
                p.sendMessage("Jetzt würden sich Kits öffnen!"); //TODO
                p.sendMessage(Data.PREFIX + "§cWähle ein Kit!");
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eGadgets")){
                GameManagement.getKitManager().openGadgetMenu(p);
                p.sendMessage(Data.PREFIX + "§cWähle ein Gadget!");
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eKits und Gagets")){
                GameManagement.getKitManager().openMenuInv(p);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAngel")){
                GagetsManager.gadget.replace(p, GagetsManager.Gadget.ANGEL);
                p.sendMessage(Data.PREFIX + "§7Du hast nun als Gagdget die Angel ausgewählt.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Enderperle")){
                GagetsManager.gadget.replace(p, GagetsManager.Gadget.ENDERPERLE);
                p.sendMessage(Data.PREFIX + "§7Du hast nun als Gagdget die Enderperle ausgewählt.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fSchnebälle")){
                GagetsManager.gadget.replace(p, GagetsManager.Gadget.SNOWBALL);
                p.sendMessage(Data.PREFIX + "§7Du hast nun als Gagdget Schneebälle ausgewählt.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
        }
    }

}
