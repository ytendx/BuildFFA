package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.mysql.SQLStats;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class DeathsListener implements Listener {

    @EventHandler
    public void onDeath(final PlayerDeathEvent e){
        final Player p = (Player) e.getEntity();
        final Player killer = p.getKiller();
        if(killer == null){
            e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " ist gestorben.");
        }
        if(SpecHandler.getSpecs().contains(killer)){
            e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " ist gestorben.");
        }else
        e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " §7wurde von §e" + killer.getName() + " §7getötet.");
        killer.sendMessage(Data.PREFIX + "§e+ 10 Bäume");
        new ActionbarAPI("§e+ §610 §7Bäume", killer).send();
        BaumAPI.getCoinsAPI().addCoins(killer, 10);
        killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1, 1);
        killer.setHealth(10);
        killer.setFoodLevel(20);
        SQLStats.addPoints(killer.getUniqueId().toString(), 2);
        p.setMaxHealth(10);
        p.setHealth(10);
        p.setFoodLevel(20);
        p.setFireTicks(0);
        if(MoveListener.getted.contains(p)){
            MoveListener.getted.remove(p);
        }
        SQLStats.addDeaths(p.getUniqueId().toString(), 1);
        SQLStats.addKills(killer.getUniqueId().toString(), 1);
        p.teleport(LocationManager.getLocation("spawn"));
        GameManagement.setInvItems(p);
        p.removePotionEffect(PotionEffectType.SPEED);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 75));
        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
    }

}
