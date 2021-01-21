package net.minebaum.buildffa.utils;

import kotlin.jvm.internal.MagicApiIntrinsics;
import net.minebaum.baumapi.api.GuiAPI;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.utils.game.MySQLConnector;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;

public class InventorySortManager {

    private Inventory inv;
    private MySQLConnector connector;
    public final int MAIN_ITEM = 0,
                    SECOND_ITEM = 1,
                    GADGET_ITEM = 2,
                    BLÖCKE_ITEM = 3;

    public InventorySortManager(MySQLConnector connector){
        this.connector = connector;
        connector.update("CREATE TABLE IF NOT EXISTS inventorysort (UUID VARCHAR(50), Mainitem INTEGER, Seconditem INTEGER, Gadgets INTEGER, Blocke INTEGER)");
    }

    public InventorySortManager(){ }

    public void sendItems(Player player, ItemStack mainitem, ItemStack seconditem, ItemStack gadgets, ItemStack blöcke){
        player.getInventory().setItem(getSlots(player)[MAIN_ITEM], mainitem);
        player.getInventory().setItem(getSlots(player)[SECOND_ITEM], seconditem);
        player.getInventory().setItem(getSlots(player)[GADGET_ITEM], gadgets);
        player.getInventory().setItem(getSlots(player)[BLÖCKE_ITEM], blöcke);
    }

    public int[] getSlots(Player p) {
        int main= 0;
        int second= 1;
        int gadget= 2;
        int blöcke= 3;
        try {
            ResultSet rs =
                    connector.query("SELECT Mainitem FROM inventorysort WHERE UUID='" +
                            p.getUniqueId().toString() + "';");
            while (rs.next())
                main = rs.getInt(1);
        } catch (Exception err) {
            System.err.println(err);
        }
        try {
            ResultSet rs =
                    connector.query("SELECT Seconditem FROM inventorysort WHERE UUID='" +
                            p.getUniqueId().toString() + "';");
            while (rs.next())
                second = rs.getInt(1);
        } catch (Exception err) {
            System.err.println(err);
        }try {
            ResultSet rs =
                    connector.query("SELECT Gadgets FROM inventorysort WHERE UUID='" +
                            p.getUniqueId().toString() + "';");
            while (rs.next())
                gadget = rs.getInt(1);
        } catch (Exception err) {
            System.err.println(err);
        }try {
            ResultSet rs =
                    connector.query("SELECT Blocke FROM inventorysort WHERE UUID='" +
                            p.getUniqueId().toString() + "';");
            while (rs.next())
                blöcke = rs.getInt(1);
        } catch (Exception err) {
            System.err.println(err);
        }
        int[] finale = new int[4];
        finale[MAIN_ITEM] = main;
        finale[SECOND_ITEM] = second;
        finale[GADGET_ITEM] = gadget;
        finale[BLÖCKE_ITEM] = blöcke;
        return finale;
    }

    public void set(Inventory inv, Player p){
        int main = 0;
        int second = 1;
        int gadgets = 2;
        int blöcke = 3;
        for(int i = 0; i < 9;i++){
            if(inv.getItem(i) != null){
                if(inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cMain-Item §7(Schwert/Knockbakcstick)")){
                    main = i;
                }else if(inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cSecond-Item §7(Pickaxe/Stick/Bow)")){
                    second = i;
                }else if(inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cBlöcke")){
                    blöcke = i;
                }else if(inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cGadget §7(Angel, Enderperle etc.)")){
                    gadgets = i;
                }
            }
        }
        boolean exist = false;
        try {
            ResultSet rs =
                    connector.query("SELECT Coins FROM inventorysort WHERE UUID='" +
                            p.getUniqueId().toString() + "';");
            while (rs.next())
                exist = Boolean.valueOf(true).booleanValue();
        } catch (Exception err) {
            System.err.println(err);
        }
        if (!exist) {
            connector.update("INSERT INTO inventorysort (UUID, Mainitem, Seconditem, Gadgets, Blocke) values ('" +
                    p.getUniqueId().toString() + "', " + main + ", " + second +", " + gadgets + ", " + blöcke + ");");}
        else{
            connector.update("UPDATE inventorysort SET Mainitem = " + main + " WHERE UUID='" + p.getUniqueId().toString() + "';");
            connector.update("UPDATE inventorysort SET Seconditem = " + second + " WHERE UUID='" + p.getUniqueId().toString() + "';");
            connector.update("UPDATE inventorysort SET Gadgets = " + gadgets + " WHERE UUID='" + p.getUniqueId().toString() + "';");
            connector.update("UPDATE inventorysort SET Blocke = " + blöcke + " WHERE UUID='" + p.getUniqueId().toString() + "';");
        }
    }

    public void openSetInventory(Player player){
        Inventory inv = new GuiAPI().GUI(9, "§eSortiere dein Inventar §8>>");
        inv.setItem(0, new ItemBuilder(Material.WOOD_SWORD, 1, (short) 0)
        .setDisplayname("§cMain-Item §7(Schwert/Knockbakcstick)")
        .build());
        inv.setItem(1, new ItemBuilder(Material.WOOD_PICKAXE, 1, (short) 0)
        .setDisplayname("§cSecond-Item §7(Pickaxe/Stick/Bow)")
        .build());
        inv.setItem(2, new ItemBuilder(Material.SANDSTONE, 1, (short) 0)
        .setDisplayname("§cBlöcke")
        .build());
        inv.setItem(8, new ItemBuilder(Material.BARRIER, 1, (short) 0)
        .setDisplayname("§cGadget §7(Angel, Enderperle etc.)")
        .build());
        player.openInventory(inv);
    }

}
