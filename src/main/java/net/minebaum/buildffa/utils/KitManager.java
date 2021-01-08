package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.api.GuiAPI;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.kits.StandartKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;

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
        Inventory inv = new GuiAPI().fillerGUI(27, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setDisplayname(" ").build(), "§eKits und Gadgets");
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("§7Diese sind deine generelle Ausrüstung.");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§7Dies sind nützliche Hilfsmittel.");
        inv.setItem(11, new ItemBuilder(Material.CHEST, 1, (short) 0).setDisplayname("§eKits").setLore(lore1).build());
        inv.setItem(13, new ItemBuilder(Material.ENDER_PEARL, 1, (short) 0).setDisplayname("§eGadgets").setLore(lore2).build());



        player.openInventory(inv);
    }


}
