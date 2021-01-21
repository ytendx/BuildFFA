package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class GagetsManager {

    public enum Gadget{

        ANGEL, ENDERPERLE, SNOWBALL, SPEED, MOREBLOCKS;

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
                if(gadget.get(p).equals(Gadget.SNOWBALL)) {
                    p.getInventory().setItem(8, new ItemBuilder(Material.SNOW_BALL, 16, (short) 0).setDisplayname("§fSchnebälle")
                            .addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                }else
                    if(gadget.get(p).equals(Gadget.SPEED)){
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, true));
                    }
            else
                {
                    p.getInventory().setItem(8, null);
                }
    }

    public static ItemStack getInvItem(Player p){
        if(!gadget.containsKey(p)){
            return new ItemBuilder(Material.AIR, 1, (short) 0).build();
        }
        if(gadget.get(p).equals(Gadget.ENDERPERLE)){
            return new ItemBuilder(Material.ENDER_PEARL, 5, (short) 0).setDisplayname("§5Enderperle").build();
        }else
        if(gadget.get(p).equals(Gadget.ANGEL)){
            return new ItemBuilder(Material.FISHING_ROD, 1, (short) 0).setUnbreakable()
                    .addEnchantment(Enchantment.KNOCKBACK, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setDisplayname("§eAngel").build();
        }else
        if(gadget.get(p).equals(Gadget.SNOWBALL)) {
            return new ItemBuilder(Material.SNOW_BALL, 16, (short) 0).setDisplayname("§fSchnebälle")
                    .addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        }else
        if(gadget.get(p).equals(Gadget.SPEED)){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, true));
            return new ItemBuilder(Material.AIR, 1, (short) 0).build();
        }else
        if(gadget.get(p).equals(Gadget.MOREBLOCKS)){
            return new ItemBuilder(Material.AIR, 1, (short) 0).build();
        }
        else
        {
            return new ItemBuilder(Material.AIR, 1, (short) 0).build();
        }
    }

}
