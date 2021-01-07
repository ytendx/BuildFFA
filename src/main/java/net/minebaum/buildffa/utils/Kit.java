package net.minebaum.buildffa.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Kit {

    private ItemStack[] itemStackList;
    private HashMap<Player, Kit> user = new HashMap<Player, Kit>();

    public HashMap<Player, Kit> getUser() {
        return user;
    }

    public ItemStack[] getItemStackList() {
        return itemStackList;
    }

    public abstract void setup();
    public abstract void setItemStacksToInventory(Player player);

    public void addUser(Player player, Kit kit){
        user.put(player, kit);
    }

    public void removeUser(Player player){
        user.remove(player);
    }

}
