package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.BuildFFA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(p.getLocation().getY() >= 90){
            e.setBuild(false);
        }else{
            Location loc = e.getBlock().getLocation();
            Bukkit.getScheduler().runTaskLater(BuildFFA.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.REDSTONE_BLOCK);
                    Bukkit.getScheduler().runTaskLater(BuildFFA.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            e.getBlock().setType(Material.AIR);
                        }
                    }, 20*2);
                }
            }, 20*3);
        }
    }

}
