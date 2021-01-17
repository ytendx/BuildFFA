package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.InventorySortManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(e.getMessage().equalsIgnoreCase("#testinv")){
            p.openInventory(new InventorySortManager(GameManagement.getConnector()).getInv(p));
        }

    }

}
