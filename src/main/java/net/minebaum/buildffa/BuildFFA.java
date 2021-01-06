package net.minebaum.buildffa;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.game.Game;
import net.minebaum.baumapi.game.Stats;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA plugin;
    private static Game g;

    public static BuildFFA getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Loaded!");
        String[] teams;
        g = new Game(BaumAPI.getPlugin(), "", "", new Stats(), 0, 50, );
        plugin = this;
    }

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("[BuildFFA] Plugin Disabled!");
    }
}
