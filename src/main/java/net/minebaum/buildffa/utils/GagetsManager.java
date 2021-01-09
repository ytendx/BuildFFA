package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.HashMap;

public class GagetsManager {

    public enum Gadget{

        ANGEL, ENDERPERLE, SNOWBALL;

    }

    public static HashMap<Player, Gadget> gadget = new HashMap<Player, Gadget>();

    public static void setInvItem(Player p){
        if(gadget.get(p).equals(Gadget.ENDERPERLE)){
            p.getInventory().setItem(8, new ItemBuilder(Material.ENDER_PEARL, 5, (short) 0).setDisplayname("§5Enderperle").build());
        }else
            if(gadget.get(p).equals(Gadget.ANGEL)){
                p.getInventory().setItem(8, new ItemBuilder(Material.FISHING_ROD, 1, (short) 0).setUnbreakable()
                        .addEnchantment(Enchantment.KNOCKBACK, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setDisplayname("§eAngel").build());
            }else
                if(gadget.get(p).equals(Gadget.SNOWBALL)){
                    p.getInventory().setItem(8, new ItemBuilder(Material.SNOW_BALL, 16, (short) 0).setDisplayname("§fSchnebälle")
                            .addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                }

            else
                {
                    p.getInventory().setItem(8, null);
                }
    }

}
