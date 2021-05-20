package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
            if(!LocationManager.isIn(e.getFrom(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2")) && LocationManager.isIn(e.getTo(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2"))){
                if(!SpecHandler.getSpecs().contains(e.getPlayer())){
                    e.setCancelled(true);
                    e.getPlayer().teleport(e.getFrom());
                    new ActionbarAPI("§cDas darfst du nicht!", e.getPlayer()).send();
                    for(Player all : Bukkit.getOnlinePlayers()){
                        if(all.hasPermission("system.spec")){
                            all.sendMessage(Data.PREFIX + "§cDer Spieler " + e.getPlayer().getName() + " versucht sich auf die Spawninsel zu buggen.");
                        }
                    }
                }
            }
        }
    }

}
