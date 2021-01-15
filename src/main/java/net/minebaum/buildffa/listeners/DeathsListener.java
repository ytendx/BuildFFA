package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.ScoreboardManagerAB;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
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
    public void onDeath(final PlayerDeathEvent e){
        final Player p = (Player) e.getEntity();
        final Player killer = p.getKiller();
        if(SpecHandler.getSpecs().contains(killer)){
            e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " ist gestorben.");
        }else
        e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " §7wurde von §e" + killer.getName() + " §7getötet.");
        killer.sendMessage(Data.PREFIX + "§e+ 10 Coins");
        new ActionbarAPI("§e+ §610 §7Coins", killer).send();
        BaumAPI.getCoinsAPI().addCoins(killer, 10);
        killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        killer.setHealth(6);
        killer.setFoodLevel(20);
        p.setMaxHealth(6);
        p.setHealth(6);
        p.setFoodLevel(20);
        p.setFireTicks(0);
        if(MoveListener.getted.contains(p)){
            MoveListener.getted.remove(p);
        }
        p.teleport(LocationManager.getLocation("spawn"));
        GameManagement.setInvItems(p);
        p.removePotionEffect(PotionEffectType.SPEED);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 75));
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
    }

}
