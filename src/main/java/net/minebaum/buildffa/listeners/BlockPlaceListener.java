package net.minebaum.buildffa.listeners;

import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.utils.LocationManager;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockPlaceListener implements Listener {

    public static ArrayList<Location> userblocks = new ArrayList<>();

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e){
        final Player p = e.getPlayer();
        if(SpecHandler.getSpecs().contains(p)){
            e.setBuild(false);
            e.setCancelled(true);
        }
        if(LocationManager.isIn(p.getLocation(), LocationManager.getLocation("pos1"), LocationManager.getLocation("pos2"))){
            e.setBuild(false);
        }else{
            Location loc = e.getBlock().getLocation();
            userblocks.add(loc);
            Bukkit.getScheduler().runTaskLater(BuildFFA.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.REDSTONE_BLOCK);
                    Bukkit.getScheduler().runTaskLater(BuildFFA.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            e.getBlock().setType(Material.AIR);
                            userblocks.remove(loc);
                        }
                    }, 20*2);
                }
            }, 20*3);
        }
    }

}
