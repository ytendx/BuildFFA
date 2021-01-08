package net.minebaum.buildffa.utils;

import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardManagerAB {

    public static void sendScoreboard(Player p) {
        ScoreboardManager sm = BuildFFA.getPlugin().getServer().getScoreboardManager();
        Scoreboard board = sm.getNewScoreboard();
        Objective obj = board.registerNewObjective("abc", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§c§lMineBaum§f.§c§lnet");
        obj.getScore("  ").setScore(10);
        obj.getScore("§8 | §cProfil").setScore(9);
        obj.getScore("§8× §7" + p.getName()).setScore(8);
        obj.getScore(" ").setScore(7);
        obj.getScore("§8 | §cCoins").setScore(6);
        obj.getScore("§8× §7" + GameManagement.getBuildFFACoins().getCoins(p)).setScore(5);
        obj.getScore("§d ").setScore(4);
        obj.getScore("§8 | §7Kit").setScore(3);
        obj.getScore("§8× §cBald verfügbar...").setScore(2);
        obj.getScore("§5 ").setScore(1);

        p.setScoreboard(board);
    }

}
