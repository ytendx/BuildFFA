package net.minebaum.buildffa;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.game.Game;
import net.minebaum.baumapi.game.GameState;
import net.minebaum.baumapi.game.Stats;
import net.minebaum.baumapi.game.StatsType;
import net.minebaum.baumapi.mysql.MySQL;

public class GameManagement {
    private static Game g;
    private static int[] gameStateIDs;
    private static GameState[] gameStates;
    private static StatsType[] statsTypes;

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

}
