package net.minebaum.buildffa.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onSpawn(final EntitySpawnEvent e){
        if(e.getEntity().getType().equals(EntityType.PLAYER) || e.getEntity().getType().equals(EntityType.SNOWBALL)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

}
