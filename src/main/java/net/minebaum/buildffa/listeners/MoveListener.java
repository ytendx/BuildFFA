package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.GagetsManager;
import net.minebaum.buildffa.utils.Kit;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.kits.StandartKit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveListener implements Listener {

    public static ArrayList<Player> getted = new ArrayList<Player>();
    public static ArrayList<Player> getted2 = new ArrayList<Player>();

    @EventHandler
    public void onMove(final PlayerMoveEvent e){
        final Player p = e.getPlayer();
        if(p.getLocation().getY() <= 1){
            if(SpecHandler.getSpecs().contains(p)){
                p.teleport(LocationManager.getLocation("spawn"));
                return;
            }
            Player killer = p.getKiller();
            boolean didAlreadeShouted = false;
            if(killer != null && !SpecHandler.getSpecs().contains(killer)){
                killer.sendMessage(Data.PREFIX + "§e+ 10 Coins");
                killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                Bukkit.broadcastMessage(Data.PREFIX + "§e" + p.getName() + " §7wurde von §e" + killer.getName() + " §7ins leere geschubst.");
                didAlreadeShouted = true;
            }
            if(!didAlreadeShouted){
                Bukkit.broadcastMessage(Data.PREFIX + "§e" + p.getName() + " ist ins leere gefallen!");
            }
            p.setMaxHealth(6);
            p.setHealth(6);
            p.setFoodLevel(20);
            p.teleport(LocationManager.getLocation("spawn"));
            p.getInventory().clear();
            GameManagement.setInvItems(p);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 75));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
        }else if(p.getLocation().getY() <= 195){
            if(getted.contains(p)){
                return;
            }else {
                if(SpecHandler.getSpecs().contains(p)){
                    return;
                }
                new ActionbarAPI("§7Auf gehts§8, §7mach dich bereit für den §cKampf§8!", p).send();
                getted.add(p);
                p.getInventory().clear();
                // TODO -> GameManagement.getMainSaver().get(p).getKit().setItemStacksToInventory(p); Getting Worked
                Kit kit = new StandartKit();
                kit.setup();
                kit.setItemStacksToInventory(p);
                GagetsManager.setInvItem(p);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
            }
        }else if(p.getLocation().getY() >= 195){
            if(MoveListener.getted.contains(p)){
                MoveListener.getted.remove(p);
            }
        }
    }

}
