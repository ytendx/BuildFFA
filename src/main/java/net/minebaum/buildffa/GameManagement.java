package net.minebaum.buildffa;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.coinapi.Coins;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.baumapi.utils.Skull.Skull;
import net.minebaum.buildffa.utils.KitInventoryMerger;
import net.minebaum.buildffa.utils.KitManager;
import net.minebaum.buildffa.utils.game.*;
import net.minebaum.buildffa.utils.game.states.IngameState;
import net.minebaum.buildffa.utils.game.states.MapSwitchState;
import net.minebaum.buildffa.utils.game.states.StatesManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class GameManagement {
    private static Game g;
    private static int[] gameStateIDs;
    private static GameState[] gameStates;
    private static StatsType[] statsTypes;
    private static HashMap<Player, KitInventoryMerger> mainSaver;
    private static MySQLConnector connector;
    private static Coins buildFFACoins;
    private static KitManager kitManager;
    public static boolean teaming = false;

    public static KitManager getKitManager() {
        return kitManager;
    }

    public static GameState[] getGameStates() {
        return gameStates;
    }

    public static HashMap<Player, KitInventoryMerger> getMainSaver() {
        return mainSaver;
    }

    public static Coins getBuildFFACoins() {
        return buildFFACoins;
    }

    public static MySQLConnector getConnector() {
        return connector;
    }

    public static Game getGame() {
        return g;
    }

    public static void setupGame(){
        try{
            connector = new MySQLConnector("web7447.cweb03.gamingweb.de", 3306, "buildffastats", "mok1382", "Ce6xNmK1O1theJAk");
            connector.connect();
        }catch (Exception e){
            e.printStackTrace();
        }
        gameStates = new GameState[2];
        gameStates[StatesManager.INGAME_STATE] = new IngameState();
        gameStates[StatesManager.MAP_CHANGE_STATE] = new MapSwitchState();
        gameStateIDs = new int[gameStates.length];
        gameStateIDs[StatesManager.INGAME_STATE] = 0;
        gameStateIDs[StatesManager.MAP_CHANGE_STATE] = 1;
        String[] teams = new String[50];
        statsTypes = new StatsType[4];
        statsTypes[0] = StatsType.POINTS;
        statsTypes[1] = StatsType.KILLS;
        statsTypes[2] = StatsType.DEATHS;
        statsTypes[3] = StatsType.KD;
        mainSaver = new HashMap<Player, KitInventoryMerger>();
        g = new Game(BuildFFA.getPlugin(),
                "BuildFFA",
                "§e§lBuildFFA",
                new Stats(statsTypes,
                        connector,
                        g),
                0,
                50,
                teams);
        g.setup(gameStateIDs, gameStates);
        kitManager = new KitManager();
        g.setCurrentGameState(gameStates[StatesManager.INGAME_STATE]);
    }

    public static void setInvItems(final Player player){
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.getInventory().setItem(4, new ItemBuilder(Material.CHEST, 1, (short) 0).setDisplayname("§eKits und Gadgets").build());
        player.getInventory().setItem(5, new ItemBuilder(Material.REDSTONE_TORCH_ON, 1, (short) 0).setDisplayname("§cInventarsortierung").build());
        ItemStack stats = Skull.getPlayerSkull(player.getName());
        ItemMeta meta = stats.getItemMeta();
        meta.setDisplayName("§cStats §8x §7" + player.getName());
        stats.setItemMeta(meta);
        player.getInventory().setItem(3, stats);
        ItemStack back = new ItemBuilder(Material.SLIME_BALL, 1, (short) 0).build();
        ItemMeta meta2 = back.getItemMeta();
        meta2.setDisplayName("§cZurück zur Lobby §8■▶");
        back.setItemMeta(meta2);
        player.getInventory().setItem(8, back);
    }

    public static void endServer(){
        //BuildFFA.getConnector().update("INSERT ");
    }

}