package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.ScoreboardManagerAB;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Score;

public class DeathsListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = (Player) e.getEntity();
        Player killer = p.getKiller();
        e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " §7wurde von §e" + killer.getName() + " §7getötet.");
        killer.sendMessage(Data.PREFIX + "§e+ 10 Coins");
        new ActionbarAPI("§e+ §610 §7Coins", killer).send();
        GameManagement.getBuildFFACoins().addCoins(killer, 10);
        killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        killer.setHealth(6);
        killer.setFoodLevel(20);
        ScoreboardManagerAB.sendScoreboard(killer);
        ScoreboardManagerAB.sendScoreboard(p);
        p.setMaxHealth(6);
        p.setHealth(6);
        p.setFoodLevel(20);
        if(MoveListener.getted.contains(p)){
            MoveListener.getted.remove(p);
        }
        p.teleport(LocationManager.getLocation("spawn"));
        GameManagement.setInvItems(p);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 75));
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
    }

}
