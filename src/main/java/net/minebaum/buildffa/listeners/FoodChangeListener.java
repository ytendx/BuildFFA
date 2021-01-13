package net.minebaum.buildffa.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeListener implements Listener {

    @EventHandler
    public void onFoodChange(final FoodLevelChangeEvent e){
        final Player p = (Player) e.getEntity();
        if(p.getLocation().getY() >= 195){
            e.setCancelled(true);
            e.setFoodLevel(20);
        }else{
            e.setFoodLevel(e.getFoodLevel() + 1);
        }
    }

}
