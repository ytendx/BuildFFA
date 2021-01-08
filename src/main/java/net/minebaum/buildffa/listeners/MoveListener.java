package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveListener implements Listener {

    public static ArrayList<Player> getted = new ArrayList<Player>();
    public static ArrayList<Player> getted2 = new ArrayList<Player>();

    @EventHandler
    public void onMove(final PlayerMoveEvent e){
        final Player p = e.getPlayer();
        if(p.getLocation().getY() <= 1){
            Player killer = p.getKiller();
            killer.sendMessage(Data.PREFIX + "§e+ 10 Coins");
            killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            p.setMaxHealth(6);
            p.setHealth(6);
            p.setFoodLevel(20);
            p.teleport(LocationManager.getLocation("spawn"));
        }else if(p.getLocation().getY() <= 90){
            if(getted.contains(p)){
                return;
            }else {
                getted.add(p);
                p.getInventory().clear();
                GameManagement.getMainSaver().get(p).getKit().setItemStacksToInventory(p);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
            }
        }else if(p.getLocation().getY() >= 90){

        }
    }

}
