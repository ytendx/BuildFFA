package net.minebaum.buildffa.listeners;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(e.getPlayer().getLocation().getY() >= 195){
            e.setCancelled(true);
        }
        if(BlockPlaceListener.userblocks.size() == 0){
            e.setCancelled(true);
        }
        for(Location all : BlockPlaceListener.userblocks){
            Location blockLoc = new Location(e.getBlock().getWorld(),
                    Math.round(e.getBlock().getLocation().getX()),
                    Math.round(e.getBlock().getLocation().getY()),
                    Math.round(e.getBlock().getLocation().getX()));
            Location playerBlockLoc = new Location(all.getWorld(),
                    Math.round(all.getX()),
                    Math.round(all.getY()),
                    Math.round(all.getZ()));
            if(blockLoc == playerBlockLoc){
                e.setCancelled(false);
                BlockPlaceListener.userblocks.remove(e.getBlock().getLocation());
                return;
            }else{
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
            }
        }
    }

}
