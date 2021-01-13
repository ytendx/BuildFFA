package net.minebaum.buildffa.utils.kits;

import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.Kit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class SpäherKit extends Kit {

    private ItemStack[] itemStackList;

    @Override
    public String getName() {
        return "SpäherKit";
    }

    @Override
    public Kit setup() {
        itemStackList = new ItemStack[4];
        itemStackList[0] = new ItemBuilder(Material.STICK, 1, (short) 0)
                .setDisplayname("§eKnockback-Stick")
                .addEnchantment(Enchantment.KNOCKBACK, 2)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        itemStackList[1] = new ItemBuilder(Material.BOW, 1, (short) 0)
                .setDisplayname("§cBogen")
                .setUnbreakable()
                .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                .build();
        itemStackList[2] = new ItemBuilder(Material.SANDSTONE, 64, (short) 0)
                .setDisplayname("§cBlöcke").build();
        itemStackList[3] = new ItemBuilder(Material.ARROW, 1, (short) 0)
                .setDisplayname("§ePfeil")
                .build();
        return this;
    }

    @Override
    public void setItemStacksToInventory(Player player) {
        if(SpecHandler.getSpecs().contains(player)){
            return;
        }
        for(int i = 1; i <= 4; i++)
            player.getInventory().setItem(i-1, itemStackList[i-1]);
    }
}
