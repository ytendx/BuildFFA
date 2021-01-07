package net.minebaum.buildffa.utils.game;

import net.minebaum.buildffa.BuildFFA;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class Game {

    private String name;
    private String prefix;
    private Stats stats;
    private GameState currentGameState;
    private ArrayList<Player> onPlayers;
    private int minPlayers;
    private int maxPlayers;
    private ArrayList<Player> specPlayers;
    private ArrayList<Player> ingamePlayers;
    private String[] teams;
    private BuildFFA plugin;
    private File gameFile;
    private YamlConfiguration confg;

    private int[] gameStates;
    private GameState[] gameStatesClasses;



    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPrefix() {return prefix;}
    public void setPrefix(String prefix) {this.prefix = prefix;}
    public Stats getStats() {return stats;}
    public void setStats(Stats stats) {this.stats = stats;}
    public GameState getCurrentGameState() {return currentGameState;}
    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
        sCMSG("");
    }
    public ArrayList<Player> getOnPlayers() {return onPlayers;}
    public void setOnPlayers(ArrayList<Player> onPlayers) {this.onPlayers = onPlayers;}
    public int getMinPlayers() {return minPlayers;}
    public void setMinPlayers(int minPlayers) {this.minPlayers = minPlayers;}
    public int getMaxPlayers() {return maxPlayers;}
    public void setMaxPlayers(int maxPlayers) {this.maxPlayers = maxPlayers;}
    public ArrayList<Player> getSpecPlayers() {return specPlayers;}
    public void setSpecPlayers(ArrayList<Player> specPlayers) {this.specPlayers = specPlayers;}
    public ArrayList<Player> getIngamePlayers() {return ingamePlayers;}
    public void setIngamePlayers(ArrayList<Player> ingamePlayers) {this.ingamePlayers = ingamePlayers;}
    public String[] getTeams() {return teams;}
    public void setTeams(String[] teams) {this.teams = teams;}
    public BuildFFA getPlugin() {return plugin;}
    public void setPlugin(BuildFFA plugin) {this.plugin = plugin;}
    public int[] getGameStates() {return gameStates;}
    public void setGameStates(int[] gameStates) {this.gameStates = gameStates;}
    public GameState[] getGameStatesClasses() {return gameStatesClasses;}
    public void setGameStatesClasses(GameState[] gameStatesClasses) {this.gameStatesClasses = gameStatesClasses;}

    public Game(BuildFFA plugin, String name, String prefix, Stats stats, int minPlayers, int maxPlayers, String[] teams) {
        this.name = name;
        this.prefix = prefix;
        this.stats = stats;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.teams = teams;
        this.plugin = plugin;
        try {
            gameFile = new File(plugin.getDataFolder(), "gameapi.yml");
        } catch (Exception e) {
            sCMSG(e.getMessage());
        }
        confg = YamlConfiguration.loadConfiguration(gameFile);
        confg.set("gamename", name.toLowerCase());
        confg.set("game.prefix", "§aSpiel");
        confg.set("game.maxplayers", maxPlayers);
        confg.set("game.minplayers", minPlayers);
    }

    public Game setup(int[] gameStates, GameState[] gameStatesClasses) {
        this.gameStates = gameStates;
        this.gameStatesClasses = gameStatesClasses;
        return this;
    }

    public Game setGameState(GameState game) {
        currentGameState = game;
        return this;
    }

    public void sCMSG(String message) {
        plugin.getServer().getConsoleSender().sendMessage("[GameAPI] " + message);
    }

    public void executeNormCMD(CommandSender sender) {
        if(sender == BuildFFA.getPlugin().getServer().getConsoleSender()) {
            sender.sendMessage(name);
            //TODO
        }
    }


}
