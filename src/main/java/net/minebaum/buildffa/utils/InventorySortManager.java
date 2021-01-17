package net.minebaum.buildffa.utils;

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

    public InventorySortManager(MySQLConnector connector){
        this.connector = connector;
        connector.update("CREATE TABLE IF NOT EXISTS inventorysort (UUID VARCHAR(50), Inventory TEXT)");
    }

    public InventorySortManager(){ }

    public void sendItems(Player player, Kit kit, ItemStack mainitem, ItemStack seconditem, ItemStack gadgets, ItemStack blöcke){
        Inventory inv = null;
        player.sendMessage("test1");
        if(getInv(player) != null){
            inv = getInv(player);
        }
        player.sendMessage("test2");
        int[] items = new int[4];
        assert inv != null;
        for(ItemStack is : inv.getContents()){
            player.getInventory().addItem(is);
            if(is.getType().equals(Material.WOOD_SWORD)){
                if(inv.getItem(0) == is){ items[0] = 0; }else if(inv.getItem(1) == is){ items[0] = 1; }else if(inv.getItem(2) == is)
                { items[0] = 2; }else if(inv.getItem(3) == is){ items[0] = 3; }else if(inv.getItem(4) == is){ items[0] = 4; }else if
                (inv.getItem(5) == is){ items[0] = 5; }else if(inv.getItem(6) == is){ items[0] = 6; }else if(inv.getItem(7) == is)
                { items[0] = 7; }else if(inv.getItem(8) == is){ items[0] = 8; }
            }else
            if(is.getType().equals(Material.WOOD_PICKAXE)){
                if(inv.getItem(0) == is){ items[1] = 0; }else if(inv.getItem(1) == is){ items[1] = 1; }else if(inv.getItem(2) == is)
                { items[1] = 2; }else if(inv.getItem(3) == is){ items[1] = 3; }else if(inv.getItem(4) == is){ items[1] = 4; }else if
                (inv.getItem(5) == is){ items[1] = 5; }else if(inv.getItem(6) == is){ items[1] = 6; }else if(inv.getItem(7) == is)
                { items[1] = 7; }else if(inv.getItem(8) == is){ items[1] = 8; }
            }else
            if(is.getType().equals(Material.SANDSTONE)){
                if(inv.getItem(0) == is){ items[2] = 0; }else if(inv.getItem(1) == is){ items[2] = 1; }else if(inv.getItem(2) == is)
                { items[2] = 2; }else if(inv.getItem(3) == is){ items[2] = 3; }else if(inv.getItem(4) == is){ items[2] = 4; }else if
                (inv.getItem(5) == is){ items[2] = 5; }else if(inv.getItem(6) == is){ items[2] = 6; }else if(inv.getItem(7) == is)
                { items[2] = 7; }else if(inv.getItem(8) == is){ items[2] = 8; }
            }else
            if(is.getType().equals(Material.BARRIER)){
                if(inv.getItem(0) == is){ items[3] = 0; }else if(inv.getItem(1) == is){ items[3] = 1; }else if(inv.getItem(2) == is)
                { items[3] = 2; }else if(inv.getItem(3) == is){ items[3] = 3; }else if(inv.getItem(4) == is){ items[3] = 4; }else if
                (inv.getItem(5) == is){ items[3] = 5; }else if(inv.getItem(6) == is){ items[3] = 6; }else if(inv.getItem(7) == is)
                { items[3] = 7; }else if(inv.getItem(8) == is){ items[3] = 8; }
            }
        }
        player.openInventory(inv);
        player.sendMessage("" + items[0]);
        player.sendMessage("" + items[1]);
        player.sendMessage("" + items[2]);
        player.sendMessage("" + items[3]);
        player.getInventory().setItem(items[0], mainitem);
        player.getInventory().setItem(items[1], seconditem);
        player.getInventory().setItem(items[2], gadgets);
        player.getInventory().setItem(items[3], blöcke);
    }

    public void set(Inventory inv, Player player){
        if(connector != null){
            boolean exist = false;
            try {
                ResultSet rs =
                        connector.query("SELECT Inventory FROM inventorysort WHERE UUID='" +
                                player.getUniqueId().toString() + "';");
                while (rs.next())
                    exist = Boolean.valueOf(true).booleanValue();
            } catch (Exception err) {
                System.err.println(err);
            }
            if (!exist) {
                connector.update("INSERT INTO inventorysort (UUID,Inventory) values ('" +
                        player.getUniqueId().toString() + "', '"
                        + toBase64(Bukkit.createInventory(null, 36)) +
                        "');");}else

            connector.update("UPDATE inventorysort SET Inventory = '" +
                    toBase64(inv)
                    + "' WHERE UUID='" + player.getUniqueId().toString() + "';");
        }

    }

    public Inventory getInv(Player player){
        Inventory inv = Bukkit.createInventory(null, 36);
        if(connector != null){

            boolean exist = false;
            try {
                ResultSet rs =
                        connector.query("SELECT Inventory FROM inventorysort WHERE UUID='" +
                                player.getUniqueId().toString() + "';");
                while (rs.next())
                    exist = Boolean.valueOf(true).booleanValue();
            } catch (Exception err) {
                System.err.println(err);
            }
            if (!exist) {
                connector.update("INSERT INTO inventorysort (UUID,Inventory) values ('" +
                        player.getUniqueId().toString() + "', '"
                        + toBase64(Bukkit.createInventory(null, 36)) +
                        "');");}

            String s = "";
            try {
                ResultSet rs =
                        connector.query("SELECT Inventory FROM inventorysort WHERE UUID='" +
                                player.getUniqueId().toString() + "';");
                while (rs.next())
                    s = rs.getString(1);
            } catch (Exception err) {
                System.err.println(err);
            }
            try {
                inv = fromBase64(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            return null;
        }
        return inv;
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

    public static String toBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(inventory.getSize());

            // Save every element in the list
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

            // Read the serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

}
