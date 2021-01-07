package net.minebaum.buildffa.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class Kit {

    private ItemStack[] itemStackList;
    private ArrayList<Player> user;

    public ArrayList<Player> getUser() {
        return user;
    }

    public ItemStack[] getItemStackList() {
        return itemStackList;
    }

    public abstract void setup();
    public abstract void setItemStacksToInventory(Player player);

    public void addUser(Player player){

    }

    public void removeUser(Player player){

    }

}
