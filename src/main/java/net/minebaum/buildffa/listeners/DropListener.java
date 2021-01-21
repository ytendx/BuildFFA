package net.minebaum.buildffa.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    @EventHandler
    public void onDrop(final PlayerDropItemEvent e){
        e.setCancelled(true);
    }

}
