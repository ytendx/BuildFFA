package net.minebaum.buildffa;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ActionbarAPI;
import net.minebaum.baumapi.coinapi.Coins;
import net.minebaum.buildffa.commands.COMMAND_Setup;
import net.minebaum.buildffa.commands.COMMAND_Spec;
import net.minebaum.buildffa.commands.COMMAND_Teaming;
import net.minebaum.buildffa.listeners.*;
import net.minebaum.buildffa.utils.ScoreboardManagerAB;
import net.minebaum.buildffa.utils.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA plugin;
    private static Game g;
    private static Coins coins;

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
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(GameManagement.teaming){
                        new ActionbarAPI("§cAchtung! §7Nur §a3er §7Teams §aerlaubt§7.", all).send();
                    }else new ActionbarAPI("§cAchtung! §cKeine Teams erlaubt!", all).send();
                }
            }
        }, 0, 40);
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
        //COMMANDS
        getCommand("setup").setExecutor(new COMMAND_Setup());
        getCommand("spec").setExecutor(new COMMAND_Spec());
        getCommand("teaming").setExecutor(new COMMAND_Teaming());
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Disabled!");
    }
}
