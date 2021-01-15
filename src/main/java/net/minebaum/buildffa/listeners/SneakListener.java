package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.GameManagement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        if(e.getPlayer().getLocation().getY() >= 195){
            GameManagement.setInvItems(e.getPlayer());
        }
    }

}
