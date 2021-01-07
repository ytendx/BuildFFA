package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathsListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = (Player) e.getEntity();
        Player killer = p.getKiller();
        e.setDeathMessage(Data.PREFIX + "§e" + p.getName() + " §7wurde von §e" + killer.getName() + " §7getötet.");
        killer.sendMessage(Data.PREFIX + "§e+ 10 Coins");
    }

}
