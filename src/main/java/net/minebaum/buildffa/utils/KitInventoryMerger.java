package net.minebaum.buildffa.utils;

import org.bukkit.entity.Player;

public class KitInventoryMerger{
    private Kit kit;
    private InventorySortManager inventorySortManager;
    private Player player;

    public KitInventoryMerger(Player player, InventorySortManager inventorySortManager, Kit kit){
        this.player = player;
        this.inventorySortManager = inventorySortManager;
        this.kit = kit;
    }

    public InventorySortManager getInventorySortManager() {
        return inventorySortManager;
    }

    public Kit getKit() {
        return kit;
    }

    public Player getPlayer() {
        return player;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setInventorySortManager(InventorySortManager inventorySortManager) {
        this.inventorySortManager = inventorySortManager;
    }

}

