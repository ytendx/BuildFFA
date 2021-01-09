package net.minebaum.buildffa.utils.spectators;

import net.minebaum.baumapi.api.GuiAPI;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.baumapi.utils.Skull.Skull;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpecHandler {

    private static ArrayList<Player> specs = new ArrayList<Player>();
    private static ItemStack nav = new ItemBuilder(Material.COMPASS, 1, (short) 0).setDisplayname("§cNavigator").setUnbreakable().build();

    public static ItemStack getNav() {
        return nav;
    }

    public static ArrayList<Player> getSpecs() {
        return specs;
    }

    public static void setSpecs(Player player){
        if(!specs.contains(player)){
            specs.add(player);
            for(Player all : Bukkit.getOnlinePlayers()){
                for(Player specs : specs){
                    all.hidePlayer(BuildFFA.getPlugin(), specs);
                }
            }
            Bukkit.broadcastMessage("§8[§c-§8] §7" + player.getName());
            player.setAllowFlight(true);
            player.setFlying(true);
            player.getInventory().clear();
            player.getInventory().setItem(0, nav);

        }else{
            for(Player all : Bukkit.getOnlinePlayers()){
                all.showPlayer(BuildFFA.getPlugin(), player);
            }
            Bukkit.broadcastMessage("§8[§a+§8] §7" + player.getName());
            player.teleport(LocationManager.getLocation("spawn"));
            player.getInventory().clear();
            player.setAllowFlight(false);
            player.setFlying(false);
            GameManagement.setInvItems(player);
            specs.remove(player);
        }
    }

    public static void update(){
        for(Player all : Bukkit.getOnlinePlayers()){
            for(Player specs : specs){
                all.hidePlayer(BuildFFA.getPlugin(), specs);
            }
        }
    }

    public static Inventory navInv(){
        Inventory inv = new GuiAPI().GUI(54, "§cOnlinespieler");

        for(Player all : Bukkit.getOnlinePlayers()){
            for(Player specs : SpecHandler.getSpecs()){
                if(specs == all){ }else{
                    ItemStack item = Skull.getPlayerSkull(all.getName());
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(all.getName());
                    item.setItemMeta(meta);
                    inv.addItem(item);
                }
            }
        }

        return inv;
    }

}
