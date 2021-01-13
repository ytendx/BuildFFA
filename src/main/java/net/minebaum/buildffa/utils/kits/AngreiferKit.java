package net.minebaum.buildffa.utils.kits;

import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.Kit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class AngreiferKit extends Kit {

    private ItemStack[] itemStackList;

    @Override
    public String getName() {
        return "AngreiferKit";
    }

    @Override
    public Kit setup() {
        itemStackList = new ItemStack[3];
        itemStackList[0] = new ItemBuilder(Material.IRON_SWORD, 1, (short) 0)
                .setDisplayname("§eSchwert")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        itemStackList[1] = new ItemBuilder(Material.WOOD_PICKAXE, 1, (short) 0)
                .setDisplayname("§cSpitzhacke")
                .addEnchantment(Enchantment.DURABILITY, 2)
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
    }
}
