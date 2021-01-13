package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ScoreboardAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.*;
import net.minebaum.buildffa.utils.kits.StandartKit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Locale;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){

        try {
            GameManagement.getGame().getStats().createAccount(e.getPlayer());
        }catch (Exception e1){
            GameManagement.getGame().sCMSG("[BuildFFA] Create Stats Account failed!");
        }

        final Player p = e.getPlayer();
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
                GameManagement.getMainSaver().put(p, new KitInventoryMerger(p, new InventorySortManager(GameManagement.getConnector()), new StandartKit()));
                GameManagement.getMainSaver().get(p).getKit().setup();
            }
        }catch (Exception e2){
            System.err.println("FEHLER IN JoinListener Exception e2! >>");
            e2.printStackTrace();
        }


        p.setMaxHealth(6);
        p.setHealth(6);
        p.setFoodLevel(20);

        GameManagement.setInvItems(p);

        SpecHandler.update();

        Bukkit.getOnlinePlayers().forEach(player -> {
            ScoreboardManagerAB.setScoreboard(p);
        });
        Bukkit.getScheduler().runTaskTimerAsynchronously(BuildFFA.getPlugin(), () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                ScoreboardManagerAB.updateTab(player);
            });

        },0,20*20);

        e.setJoinMessage(Data.PREFIX + "§7Der Spieler §e" + e.getPlayer().getDisplayName() + "§7 ist BuildFFA beigetreten§8.");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        MoveListener.getted.remove(e.getPlayer());
        if(SpecHandler.getSpecs().contains(e.getPlayer())){
            SpecHandler.setSpecs(e.getPlayer());
        }
        e.setQuitMessage(Data.PREFIX + "§7Der Spieler §e" + e.getPlayer().getDisplayName() + "§7 ist GunGame verlassen§8.");
    }

    public HashMap<Player, Integer> level = new HashMap<>();

    public void set(){
       int i = level.get(Bukkit.getPlayer("dad"));
       Player p = Bukkit.getPlayer("ytendx");
       if(!level.containsKey(p)){
           level.put(p, 1);
       }
       p.setLevel(level.get(p));
    }

}
