package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.api.ActionbarAPI;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER){
            if(e.getEntity().getLocation().getY() >= 195){
                e.setDamage(0);
                e.setCancelled(true);
                new ActionbarAPI("§cDas darfst du hier nicht!", (Player) e.getDamager()).send();
            }
            Player p = (Player) e.getDamager();
            Player p2 = (Player) e.getEntity();
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1F, 1F);
        }
    }

}
