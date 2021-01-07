package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.api.GuiAPI;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.kits.StandartKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class KitManager {

    private Inventory inv;
    private Kit[] kits;

    public KitManager(){
        kits = new Kit[3];
        kits[0] = new StandartKit();

        inv = new GuiAPI().fillerGUI(9*3,
                new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setDisplayname(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(),
                "§eWähle ein Kit!");
        inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setDisplayname(" ").build());
        inv.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setDisplayname(" ").build());
        inv.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setDisplayname(" ").build());
        inv.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setDisplayname(" ").build());
    }

    public void openMenuInv(Player player){

    }

}
