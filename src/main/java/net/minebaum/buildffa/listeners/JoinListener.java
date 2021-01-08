package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ScoreboardAPI;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.KitInventoryMerger;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.ScoreboardManagerAB;
import net.minebaum.buildffa.utils.kits.StandartKit;
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
        try {
            p.teleport(LocationManager.getLocation("spawn"));
        }catch (Exception e1){
            BuildFFA.getPlugin().getServer().getConsoleSender().sendMessage(e1.getMessage());
        }

        try {
            if(!GameManagement.getMainSaver().containsKey(p)){
                GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(p), new StandartKit()));
            }
        }catch (Exception e2){
            System.err.println("FEHLER IN JoinListener Exception e2! >>");
            e2.printStackTrace();
        }

        ScoreboardManagerAB.sendScoreboard(p);

        p.setMaxHealth(6);
        p.setHealth(6);
        p.setFoodLevel(20);

        e.setJoinMessage("§8[§a+§8] §7" + e.getPlayer().getName());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage("§8[§c-§8] §7" + e.getPlayer().getName());
    }

}
