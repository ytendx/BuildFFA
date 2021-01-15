package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.time.LocalDateTime;
import java.time.temporal.*;
import java.util.Date;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onHit(final EntityDamageByEntityEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER){
            if(SpecHandler.getSpecs().contains((Player) e.getDamager())){
                if(SpecHandler.getChecks().contains(((Player) e.getDamager()).getPlayer())){
                    e.setCancelled(false);
                }else{
                    new ActionbarAPI("§cAktiviere den ChekcMode!", ((Player) e.getDamager()).getPlayer()).send();
                    e.setCancelled(true);
                }
            }
            if(e.getEntity().getLocation().getY() >= 195){
                e.setDamage(0);
                e.setCancelled(true);
                new ActionbarAPI("§cDas darfst du hier nicht!", (Player) e.getDamager()).send();
            }
            Player p = (Player) e.getDamager();
            Player p2 = (Player) e.getEntity();
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1F, 1F);
        }else if(e.getDamager().getType() == EntityType.SNOWBALL){
            e.setDamage(1);
            e.setCancelled(false);
        }
    }

}
