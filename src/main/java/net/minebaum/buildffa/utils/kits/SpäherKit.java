package net.minebaum.buildffa.utils.kits;

import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.GagetsManager;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.Kit;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpäherKit extends Kit {

    private ItemStack[] itemStackList;

    @Override
    public String getName() {
        return "Späher";
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
                .build();
        itemStackList[2] = new ItemBuilder(Material.SANDSTONE, 64, (short) 0)
                .setDisplayname("§cBlöcke").build();
        itemStackList[3] = new ItemBuilder(Material.ARROW, 3, (short) 0)
                .setDisplayname("§ePfeil")
                .build();
        return this;
    }

    @Override
    public void setItemStacksToInventory(Player player) {
        if(SpecHandler.getSpecs().contains(player)){
            return;
        }
        int currentJob = net.minebaum.buildffa.utils.EventHandler.getCurrentEvent();
        Player all = player;
        if(currentJob == net.minebaum.buildffa.utils.EventHandler.SLOWNESS){
            all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 2));
        }else if(currentJob == net.minebaum.buildffa.utils.EventHandler.JUMP_SPEED){
            all.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, 2));
        }
        new InventorySortManager(GameManagement.getConnector()).sendItems(player, itemStackList[0], itemStackList[1], GagetsManager.getInvItem(player), itemStackList[2]);
        player.getInventory().setItem(9, itemStackList[3]);
    }
}
