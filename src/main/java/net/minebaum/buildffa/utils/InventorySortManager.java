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

    public void set(Inventory inv, Player player){
        if(connector != null){
            boolean exist = false;
            try {
                ResultSet rs =
                        connector.query("SELECT Coins FROM inventorysort WHERE UUID='" +
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
                        connector.query("SELECT Coins FROM inventorysort WHERE UUID='" +
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
