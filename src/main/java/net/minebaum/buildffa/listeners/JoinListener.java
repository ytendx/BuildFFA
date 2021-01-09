package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ScoreboardAPI;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.*;
import net.minebaum.buildffa.utils.kits.StandartKit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Locale;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        try {
            GameManagement.getGame().getStats().createAccount(e.getPlayer());
        }catch (Exception e1){
            GameManagement.getGame().sCMSG("[BuildFFA] Create Stats Account failed!");
        }

        Player p = e.getPlayer();
        p.getInventory().clear();

        if(!GagetsManager.gadget.containsKey(p)){
            GagetsManager.gadget.put(p, null);
        }

        try {
            p.teleport(LocationManager.getLocation("spawn"));
        }catch (Exception e1){
            BuildFFA.getPlugin().getServer().getConsoleSender().sendMessage(e1.getMessage());
        }

        try {
            if(!GameManagement.getMainSaver().containsKey(p)){
                GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(p), new StandartKit()));
                GameManagement.getMainSaver().get(p).getKit().setup();
            }
        }catch (Exception e2){
            System.err.println("FEHLER IN JoinListener Exception e2! >>");
            e2.printStackTrace();
        }

        ScoreboardManagerAB.sendScoreboard(p);

        p.setMaxHealth(6);
        p.setHealth(6);
        p.setFoodLevel(20);

        GameManagement.setInvItems(p);

        SpecHandler.update();

        e.setJoinMessage("§8[§a+§8] §7" + e.getPlayer().getName());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        MoveListener.getted.remove(e.getPlayer());
        if(SpecHandler.getSpecs().contains(e.getPlayer())){
            SpecHandler.setSpecs(e.getPlayer());
        }
        e.setQuitMessage("§8[§c-§8] §7" + e.getPlayer().getName());
    }

}
