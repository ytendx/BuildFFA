package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.GagetsManager;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.KitInventoryMerger;
import net.minebaum.buildffa.utils.kits.*;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(final InventoryClickEvent e){
        final Player p = (Player)e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§cOnlinespieler")){
            e.setCancelled(true);
            final Player target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
            if(target != null){
                p.teleport(target);
                p.closeInventory();
            }
        }
        if(e.getInventory().getTitle().equalsIgnoreCase("§eSortiere dein Inventar §8>>")){
            e.setCancelled(false);
            return;
        }
        if(e.getInventory().getTitle().equalsIgnoreCase("§cEinstellungen")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("An")){
                SpecHandler.getChecks().remove(p);
                p.closeInventory();
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Aus")){
                if(p.hasPermission("system.spec.check")){
                    if(SpecHandler.getChecks().contains(p)){
                        p.closeInventory();
                        return;
                    }else{
                        p.closeInventory();
                        SpecHandler.getChecks().add(p);
                    }
                }else{
                    p.sendTitle("§cFehler", "§cKeine Rechte!");
                    p.closeInventory();
                }
            }
        }
        if(p.getLocation().getY() >= 195){
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eKits")){
                GameManagement.getKitManager().openKitMenu(p);
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
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Speed")){
                GagetsManager.gadget.replace(p, GagetsManager.Gadget.SPEED);
                p.sendMessage(Data.PREFIX + "§7Du hast nun als Gagdget Speed ausgewählt.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fAngreiferKit")){
                if(GameManagement.getMainSaver().containsKey(p)){
                    GameManagement.getMainSaver().get(p).setKit(new AngreiferKit().setup());
                }else{
                    GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(GameManagement.getConnector()), new AngreiferKit().setup()));
                }
                p.sendMessage(Data.PREFIX + "§eDu hast dein Kit erfolgreich zu " + GameManagement.getMainSaver().get(p).getKit().getName());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPyroKit")){
                if(GameManagement.getMainSaver().containsKey(p)){
                    GameManagement.getMainSaver().get(p).setKit(new PyroKit().setup());
                }else{
                    GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(GameManagement.getConnector()), new PyroKit().setup()));
                }
                p.sendMessage(Data.PREFIX + "§eDu hast dein Kit erfolgreich zu " + GameManagement.getMainSaver().get(p).getKit().getName());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3SpäherKit")){
                if(GameManagement.getMainSaver().containsKey(p)){
                    GameManagement.getMainSaver().get(p).setKit(new SpäherKit().setup());
                }else{
                    GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(GameManagement.getConnector()), new SpäherKit().setup()));
                }
                p.sendMessage(Data.PREFIX + "§eDu hast dein Kit erfolgreich zu " + GameManagement.getMainSaver().get(p).getKit().getName());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§fSchne")){
                GagetsManager.gadget.replace(p, GagetsManager.Gadget.SNOWBALL);
                p.sendMessage(Data.PREFIX + "§7Du hast nun als Gagdget Schneebälle ausgewählt.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eStandartkit")){
                if(GameManagement.getMainSaver().containsKey(p)){
                    GameManagement.getMainSaver().get(p).setKit(new StandartKit().setup());
                }else{
                    GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(GameManagement.getConnector()), new StandartKit().setup()));
                }
                p.sendMessage(Data.PREFIX + "§eDu hast dein Kit erfolgreich zu " + GameManagement.getMainSaver().get(p).getKit().getName());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bTankKit")){
                if(GameManagement.getMainSaver().containsKey(p)){
                    GameManagement.getMainSaver().get(p).setKit(new TankKit().setup());
                }else{
                    GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(GameManagement.getConnector()), new TankKit().setup()));
                }
                p.sendMessage(Data.PREFIX + "§eDu hast dein Kit erfolgreich zu " + GameManagement.getMainSaver().get(p).getKit().getName());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
            if(e.getInventory().getTitle().equalsIgnoreCase("§eKits und Gadgets")){
                e.setCancelled(true);
            }
        }
    }

}
