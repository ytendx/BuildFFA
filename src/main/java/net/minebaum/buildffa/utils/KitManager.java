package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.api.GuiAPI;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.kits.StandartKit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

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
        inv.setItem(15, new ItemBuilder(Material.ENDER_PEARL, 1, (short) 0).setDisplayname("§eGadgets").setLore(lore2).build());

        player.openInventory(inv);
    }

    public void openGadgetMenu(Player player){
        Inventory inv = new GuiAPI().fillerGUI(27, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setDisplayname(" ").build(), "§eWähle ein Gadget >>");
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("§7Damit kannst du andere Spieler angeln.");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§7Damit kannst du dich durch die Map teleportieren.");
        ArrayList<String> lore3 = new ArrayList<String>();
        lore3.add("§7Damit kannst du andere Spieler mit Schneebällen bewerfen.");
        ArrayList<String> lore4 = new ArrayList<String>();
        lore4.add("§7Damit wirst du schneller.");
        inv.setItem(10, new ItemBuilder(Material.FISHING_ROD, 1, (short) 0).setDisplayname("§cAngel").setLore(lore1).build());
        inv.setItem(11, new ItemBuilder(Material.ENDER_PEARL, 1, (short) 0).setDisplayname("§5Enderperle").setLore(lore2).build());
        inv.setItem(12, new ItemBuilder(Material.SNOW_BALL, 1, (short) 0).setDisplayname("§fSchneebälle").setLore(lore3).build());
        inv.setItem(13, new ItemBuilder(Material.FEATHER, 1, (short) 0).setDisplayname("§3Speed").setLore(lore4).build());
        player.openInventory(inv);
    }

    public void openKitMenu(Player player){
        Inventory inv = new GuiAPI().fillerGUI(27, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setDisplayname(" ").build(), "§eWähle ein Kit >>");
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("§7Das ist das Standartkit.");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§7Das ist ein Rüstungskit.");
        ArrayList<String> lore3 = new ArrayList<String>();
        lore3.add("§7Du erhältst ein extra starkes Schwert.");
        ArrayList<String> lore4 = new ArrayList<String>();
        lore4.add("§7Du erhälst einen Bogen.");
        inv.setItem(10, new ItemBuilder(Material.WOOD_PICKAXE, 1, (short) 0).setDisplayname("§eStandartkit").setLore(lore1).build());
        inv.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1, (short) 0).setDisplayname("§bTankKit").setLore(lore2).build());
        inv.setItem(12, new ItemBuilder(Material.DIAMOND_SWORD, 1, (short) 0).setDisplayname("§fAngreiferKit").setLore(lore3).build());
        inv.setItem(13, new ItemBuilder(Material.BOW, 1, (short) 0).setDisplayname("§3SpäherKit").setLore(lore4).build());
        player.openInventory(inv);
    }


}
