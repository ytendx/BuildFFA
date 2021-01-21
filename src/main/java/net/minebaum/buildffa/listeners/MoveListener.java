package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.ScoreboardManager;
import net.minebaum.buildffa.utils.mysql.SQLStats;
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

public class MoveListener implements Listener {

    public static ArrayList<Player> getted = new ArrayList<Player>();
    public static ArrayList<Player> getted2 = new ArrayList<Player>();

    @EventHandler
    public void onMove(final PlayerMoveEvent e){
        final Player p = e.getPlayer();
        if(e.getTo().getY() >= 195 && e.getFrom().getY() <= 195 && !SpecHandler.getSpecs().contains(p)){
            p.teleport(e.getFrom());
            new ActionbarAPI("§cDas darfst du nicht!", p).send();
            for(Player all : Bukkit.getOnlinePlayers()){
                if(all.hasPermission("system.spec")){
                    all.sendMessage(Data.PREFIX + "§cDer Spieler " + p.getName() + " versucht sich auf die Spawninsel zu buggen.");
                }
            }
        }
        if(p.getLocation().getY() <= 1){
            if(SpecHandler.getSpecs().contains(p)){
                p.teleport(LocationManager.getLocation("spawn"));
                return;
            }
            final Player killer = p.getKiller();
            boolean didAlreadeShouted = false;
            if(killer != null && !SpecHandler.getSpecs().contains(killer)){
                killer.sendMessage(Data.PREFIX + "§e+ 10 Coins");
                killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                BaumAPI.getCoinsAPI().addCoins(killer, 10);
                Bukkit.broadcastMessage(Data.PREFIX + "§e" + p.getName() + " §7wurde von §e" + killer.getName() + " §7ins leere geschubst.");
                SQLStats.addDeaths(p.getUniqueId().toString(), 1);
                SQLStats.addKills(killer.getUniqueId().toString(), 1);
                ScoreboardManager.set(p);
                ScoreboardManager.set(killer);
                didAlreadeShouted = true;
            }
            if(!didAlreadeShouted){
                Bukkit.broadcastMessage(Data.PREFIX + "§e" + p.getName() + " ist ins leere gefallen!");
                ScoreboardManager.set(p);
                SQLStats.addDeaths(p.getUniqueId().toString(), 1);
            }
            p.setMaxHealth(6);
            p.setHealth(6);
            p.setFoodLevel(20);
            p.setFireTicks(0);
            p.teleport(LocationManager.getLocation("spawn"));
            p.getInventory().clear();
            GameManagement.setInvItems(p);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 75));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
        }else if(p.getLocation().getY() <= 195){
            if(p.getOpenInventory().getTitle().equalsIgnoreCase("§eWähle ein Gadget >>")){
                p.closeInventory();
            }else if(p.getOpenInventory().getTitle().equalsIgnoreCase("§eWähle ein Kit >>")){
                p.closeInventory();
            }else if(p.getOpenInventory().getTitle().equalsIgnoreCase("§eKits und Gadgets")){
                p.closeInventory();
            }
            if(getted.contains(p)){
                return;
            }else {
                if(SpecHandler.getSpecs().contains(p)){
                    return;
                }
                new ActionbarAPI("§7Auf gehts§8, §7mach dich bereit für den §cKampf§8!", p).send();
                getted.add(p);
                p.getInventory().clear();
                GameManagement.getMainSaver().get(p).getKit().setItemStacksToInventory(p);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
            }
        }else if(p.getLocation().getY() >= 195){
            if(p.getActivePotionEffects() != null){
                for(final PotionEffect pe : p.getActivePotionEffects()){
                    p.removePotionEffect(pe.getType());
                }
            }
            if(MoveListener.getted.contains(p)){
                MoveListener.getted.remove(p);
            }
        }
    }

}
