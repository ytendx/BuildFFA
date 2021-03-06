package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onHit(final EntityDamageByEntityEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER){
            if(!LocationManager.isIn(e.getDamager().getLocation(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2")) && LocationManager.isIn(e.getEntity().getLocation(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2"))){
                e.setCancelled(true);
                e.setDamage(0);
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(all.hasPermission("system.spec")){
                        all.sendMessage(Data.PREFIX + "§cDer Spieler §e" + e.getEntity().getName() + " §cversucht eine Spieler auf der Spawninsel zu hitten. (Bugausnutzung)");
                    }
                }
            }
        }
        if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER){
            if(SpecHandler.getSpecs().contains((Player) e.getDamager())){
                if(SpecHandler.getChecks().contains(((Player) e.getDamager()).getPlayer())){
                    e.setCancelled(false);
                }else{
                    new ActionbarAPI("§cAktiviere den ChekcMode!", ((Player) e.getDamager()).getPlayer()).send();
                    e.setCancelled(true);
                }
            }
            if(LocationManager.isIn(e.getEntity().getLocation(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2"))){
                e.setDamage(0);
                e.setCancelled(true);
                new ActionbarAPI("§cDas darfst du hier nicht!", (Player) e.getDamager()).send();
                Player p = (Player) e.getDamager();
                Player p2 = (Player) e.getEntity();
                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1F, 1F);
            }
            Player p = (Player) e.getDamager();
            Player p2 = (Player) e.getEntity();
        }else if(e.getDamager().getType() == EntityType.SNOWBALL){
            e.setDamage(1);
            e.setCancelled(false);
        }
    }

}
