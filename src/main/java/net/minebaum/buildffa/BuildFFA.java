package net.minebaum.buildffa;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.coinapi.Coins;
import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.commands.*;
import net.minebaum.buildffa.listeners.*;
import net.minebaum.buildffa.utils.EventHandler;
import net.minebaum.buildffa.utils.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA plugin;
    private static Game g;
    private static Coins coins;
    private int count = 0;

    public static Coins getCoins() {
        return coins;
    }

    public static Game getGame() {
        return g;
    }

    public static BuildFFA getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Loaded!");
        plugin = this;
    }

    @Override
    public void onEnable() {
        //this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Enabled!");
        GameManagement.setupGame();
        g = GameManagement.getGame();
        register();
        coins = new Coins(BaumAPI.getPlugin());
        coins.setupMySQL("mok13820", "Ce6xNmK1O1theJAk", "web7447.cweb03.gamingweb.de", 3306, "coinapi");
        coins.setup(0);
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Enabled!");

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                if(count == 6){
                    count = 0;
                }
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.sendMessage(news(count));
                }
                count++;
            }
        }, 0, 20*60);
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(GameManagement.teaming){
                        new ActionbarAPI("??cAchtung! ??7Nur ??a3er ??7Teams ??aerlaubt??7.", all).send();
                    }else new ActionbarAPI("??cAchtung! ??cKeine Teams erlaubt!", all).send();
                }
            }
        }, 0, 40);
        new EventHandler();
        GameManagement.getConnector().update("CREATE TABLE IF NOT EXISTS Stats(UUID varchar(65), KILLS int,DEATHS int,POINTS int);");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doFireTick false");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule announceAdvancements false");
    }

    private void register() {
        // LISTENERS
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new DeathsListener(), this);
        pm.registerEvents(new MoveListener(), this);
        pm.registerEvents(new BlockPlaceListener(), this);
        pm.registerEvents(new InteractListener(), this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new FoodChangeListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new WeatherChangeListener(), this);
        pm.registerEvents(new MobSpawnListener(), this);
        pm.registerEvents(new DropListener(), this);
        pm.registerEvents(new CloseInventory(), this);
        pm.registerEvents(new TeleportListener(), this);
        pm.registerEvents(new SneakListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new InventoryOpenListener(), this);
        //COMMANDS
        getCommand("setup").setExecutor(new COMMAND_Setup());
        getCommand("spec").setExecutor(new COMMAND_Spec());
        getCommand("teaming").setExecutor(new COMMAND_Teaming());
        getCommand("stats").setExecutor(new Stats());
        getCommand("fix").setExecutor(new FixCommand());
    }

    private String news(int count){
        if(count == 0){
            return Data.PREFIX + "??eDeine Items buggen? ??a-> ??e*SNEAKEN*";
        }else if(count == 1){
            return Data.PREFIX + "??cOb Teaming erlaubt ist, siehst du in der Actionbar!";
        }else if(count == 2){
            return Data.PREFIX + "??eKaufe dir Kits in der Lobby! ??8-> ??e/shop";
        }else if(count == 3){
            return Data.PREFIX + "??cSpammt nicht mit B??gen!";
        }else if(count == 4){
            return Data.PREFIX + "??cDer Versuch von Bugusing ist verboten und wird bestraft!";
        }else{
            return Data.PREFIX + "??eDu befindest dich auf BuildFFA von ??c??lMineBaum??8.";
        }
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Disabled!");
    }
}
