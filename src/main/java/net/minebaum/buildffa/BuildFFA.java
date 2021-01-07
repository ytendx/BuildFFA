package net.minebaum.buildffa;

import net.minebaum.baumapi.game.Game;
import net.minebaum.baumapi.mysql.MySQLConnector;
import net.minebaum.buildffa.commands.COMMAND_Setup;
import net.minebaum.buildffa.listeners.EntityDamageListener;
import net.minebaum.buildffa.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA plugin;
    private static Game g;

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


        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Enabled!");
    }

    private void register() {
        // LISTENERS
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        //COMMANDS
        getCommand("setup").setExecutor(new COMMAND_Setup());
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Disabled!");
    }
}
