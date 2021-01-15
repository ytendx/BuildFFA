package net.minebaum.buildffa.utils.kits;

import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.Kit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PyroKit extends Kit {

    private ItemStack[] itemStackList;

    @Override
    public String getName() {
        return "PyroKit";
    }

    @Override
    public Kit setup() {
        itemStackList = new ItemStack[3];
        itemStackList[0] = new ItemBuilder(Material.WOOD_SWORD, 1, (short) 0)
                .setDisplayname("§eSchwert")
                .addEnchantment(Enchantment.FIRE_ASPECT, 1)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        itemStackList[1] = new ItemBuilder(Material.FLINT_AND_STEEL, 1, (short) 0)
                .setDisplayname("§cFeuerzeug")
                .setUnbreakable()
                .build();
        itemStackList[2] = new ItemBuilder(Material.SANDSTONE, 64, (short) 0)
                .setDisplayname("§cBlöcke").build();
        return this;
    }

    @Override
    public void setItemStacksToInventory(Player player) {
        if(SpecHandler.getSpecs().contains(player)){
            return;
        }
        for(int i = 1; i <= 3; i++)
            player.getInventory().setItem(i-1, itemStackList[i-1]);
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
        player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, (short) 0).setUnbreakable().build());
        player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, (short) 0).setUnbreakable().build());
        player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1, (short) 0).setUnbreakable().build());
        player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1, (short) 0).setUnbreakable().build());
    }
}