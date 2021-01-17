package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.BaumAPI;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManagerAB {

    public static void setScoreboard(Player p) {
        Scoreboard sb;
        Objective obj;
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = sb.registerNewObjective("aaa","dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§c§lMineBaum §8| §7BuildFFA");
        Team coins = sb.registerNewTeam("coins");
        Team onlinetime = sb.registerNewTeam("onlinetime");
        obj.getScore("").setScore(10);
        obj.getScore("§8 | §cProfil").setScore(9);
        obj.getScore("§8× §7" + p.getName()).setScore(8);
        obj.getScore(" ").setScore(7);
        obj.getScore("§8 | §aBäume").setScore(6);
        obj.getScore("§5").setScore(5);
        obj.getScore("   ").setScore(4);
        obj.getScore("§8 | §cKit").setScore(3);
        obj.getScore("§8× Standart").setScore(2);
        obj.getScore("    ").setScore(1);
        obj.getScore("§7§m-------------------").setScore(0);
        coins.addEntry("§5");
        coins.setPrefix("§8× §7" + BaumAPI.getCoinsAPI().getCoins(p));
        onlinetime.addEntry("§7");
        onlinetime.setPrefix("§8× §7" + GameManagement.getMainSaver().get(p).getKit().getName());
        p.setScoreboard(sb);
        Bukkit.getScheduler().runTaskTimerAsynchronously(BuildFFA.getPlugin(), () -> {
            coins.setPrefix("§8× §7"+ BaumAPI.getCoinsAPI().getCoins(p));
            onlinetime.setPrefix("§8× §7" + GameManagement.getMainSaver().get(p).getKit().getName());
        },0,20);


    }


}
