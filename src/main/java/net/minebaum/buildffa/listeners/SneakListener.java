package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        if(LocationManager.isIn(e.getPlayer().getLocation(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2"))){
            GameManagement.setInvItems(e.getPlayer());
        }
    }

}
