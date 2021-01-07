package net.minebaum.buildffa.listeners;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.baumapi.api.ScoreboardAPI;
import net.minebaum.baumapi.game.Game;
import net.minebaum.buildffa.GameManagement;
import net.minebaum.buildffa.utils.LocationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Locale;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        String[] teams = GameManagement.getGame().getTeams();
        teams[GameManagement.getGame().getTeams().length] = e.getPlayer().getName().toUpperCase();
        GameManagement.getGame().setTeams(teams);

        try {
            GameManagement.getGame().getStats().createAccount(e.getPlayer());
        }catch (Exception e1){
            GameManagement.getGame().sCMSG("[BuildFFA] Create Stats Account failed!");
        }

        Player p = e.getPlayer();
        try {
            p.teleport(LocationManager.getLocation("spawn"));
        }catch (Exception e1){
            BaumAPI.getPlugin().getServer().getConsoleSender().sendMessage(e1.getMessage());
        }

        ScoreboardAPI api = new ScoreboardAPI(BaumAPI.getPlugin(), "§c§lMineBaum§f.§c§lnet");
        api.addType("", "");
        api.send(e.getPlayer());

        e.setJoinMessage("§8[§a+§8] §7" + e.getPlayer().getName());

    }

}
