package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ScoreboardAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.*;
import net.minebaum.buildffa.utils.kits.StandartKit;
import net.minebaum.buildffa.utils.mysql.SQLStats;
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
        final Player p = e.getPlayer();
        SQLStats.createPlayer(p.getUniqueId().toString());

        if(!e.getPlayer().hasPlayedBefore()){
            SQLStats.addPoints(e.getPlayer().getUniqueId().toString(), 3);
        }

        p.getInventory().clear();

        if(!GagetsManager.gadget.containsKey(p)){
            GagetsManager.gadget.put(p, GagetsManager.Gadget.MOREBLOCKS);
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
        ScoreboardManager.set(p);

        e.setJoinMessage(Data.PREFIX + "§7Der Spieler §e" + e.getPlayer().getDisplayName() + "§7 hat BuildFFA beigetreten§8.");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        MoveListener.getted.remove(e.getPlayer());
        if(SpecHandler.getSpecs().contains(e.getPlayer())){
            SpecHandler.setSpecs(e.getPlayer());
        }
        e.setQuitMessage(Data.PREFIX + "§7Der Spieler §e" + e.getPlayer().getDisplayName() + "§7 hat BuildFFA verlassen§8.");
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
