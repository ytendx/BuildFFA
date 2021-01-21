package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(final EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            e.setCancelled(true);
        }else if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)){
            if(e.getEntity().getLocation().getY() >= 195){
                e.setCancelled(true);
                e.setDamage(0);
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(all.hasPermission("system.spec")){
                        all.sendMessage(Data.PREFIX + "§cDer Spieler " + e.getEntity().getName() + " wurde von einem Spieler versucht, auf der Spwninsel, zu hitten. (Bugusing)");
                    }
                }
            }
        }
    }

}
