package net.minebaum.buildffa;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.game.Game;
import net.minebaum.baumapi.game.GameState;
import net.minebaum.baumapi.game.Stats;
import net.minebaum.baumapi.game.StatsType;
import net.minebaum.baumapi.mysql.MySQL;
import net.minebaum.buildffa.utils.InventorySortManager;
import net.minebaum.buildffa.utils.Kit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameManagement {
    private static Game g;
    private static int[] gameStateIDs;
    private static GameState[] gameStates;
    private static StatsType[] statsTypes;
    private static HashMap<Player, KitInventoryMerger> mainSaver;

    public static Game getGame() {
        return g;
    }

    public static void setupGame(){
        String[] teams = new String[50];
        statsTypes = new StatsType[4];
        statsTypes[0] = StatsType.POINTS;
        statsTypes[1] = StatsType.KILLS;
        statsTypes[2] = StatsType.DEATHS;
        statsTypes[3] = StatsType.KD;
        g = new Game(BaumAPI.getPlugin(), "BuildFFA", "§e§lBuildFFA", new Stats(statsTypes, BuildFFA.getConnector(), BuildFFA.getGame()), 0, 50, teams);
        gameStateIDs = new int[1];
        gameStates = new GameState[gameStateIDs.length];
        g.setup(gameStateIDs, gameStates);
    }

    public static void endServer(){
        //BuildFFA.getConnector().update("INSERT ");
    }

}

class KitInventoryMerger{
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
