package net.minebaum.buildffa.utils.kits;

import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.Kit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class TankKit extends Kit {

    private ItemStack[] itemStackList;

    @Override
    public String getName() {
        return "TankKit";
    }

    @Override
    public TankKit setup() {
        itemStackList = new ItemStack[3];
        itemStackList[0] = new ItemBuilder(Material.STONE_SWORD, 1, (short) 0)
                .setDisplayname("§eSchwert")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        itemStackList[1] = new ItemBuilder(Material.STICK, 1, (short) 0)
                .setDisplayname("§eKnockback-Stick")
                .addEnchantment(Enchantment.KNOCKBACK, 1)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
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
        player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET, 1, (short) 0).setDisplayname("Helm").build());
        player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1, (short) 0).setDisplayname("Chestplate").build());
        player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1, (short) 0).setDisplayname("Hose").build());
        player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1, (short) 0).setDisplayname("Nike Air").build());
    }
}
