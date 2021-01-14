package net.minebaum.buildffa.utils;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import net.minebaum.baumapi.BaumAPI;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.GameManagement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.concurrent.TimeUnit;

public class ScoreboardManagerAB {

    public static void sendScoreboard(Player p) {
        ScoreboardManager sm = BuildFFA.getPlugin().getServer().getScoreboardManager();
        Scoreboard board = sm.getNewScoreboard();
        Objective obj = board.registerNewObjective("abc", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§c§lMineBaum §8| §7BuildFFA");
        obj.getScore("  ").setScore(10);
        obj.getScore("§8 | §cProfil").setScore(9);
        obj.getScore("§8× §7" + p.getName()).setScore(8);
        obj.getScore(" ").setScore(7);
        obj.getScore("§8 | §aBäume").setScore(6);
        obj.getScore("§8× §7" + BaumAPI.getCoinsAPI().getCoins(p)).setScore(5);
        obj.getScore("§d ").setScore(4);
        obj.getScore("§8 | §7Kit").setScore(3);
        obj.getScore("§8× §7" + GameManagement.getMainSaver().get(p).getKit().getName()).setScore(2);
        obj.getScore("§5 ").setScore(1);

        p.setScoreboard(board);
    }

    public static void setScoreboard(Player p) {
        Scoreboard sb;
        Objective obj;
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        ICloudPlayer player = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(p.getUniqueId());
        long millis = player.getOnlineTime();
        Integer hours = Math.toIntExact(TimeUnit.MILLISECONDS.toHours(millis));
        obj = sb.registerNewObjective("aaa","dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§c§lMineBaum §8| §7GunGame");
        Team coins = sb.registerNewTeam("coins");
        Team onlinetime = sb.registerNewTeam("onlinetime");
        obj.getScore("").setScore(10);
        obj.getScore("§8 | §cProfil").setScore(9);
        obj.getScore("§8× §7" + p.getName()).setScore(8);
        obj.getScore(" ").setScore(7);
        obj.getScore("§8 | §aBäume").setScore(6);
        obj.getScore("§5").setScore(5);
        obj.getScore("   ").setScore(4);
        obj.getScore("§8 | §cDeine Level").setScore(3);
        obj.getScore("§7").setScore(2);
        obj.getScore("    ").setScore(1);
        obj.getScore("§7§m-------------------").setScore(0);
        coins.addEntry("§5");
        coins.setPrefix("§8× §7");
        onlinetime.addEntry("§7");
        onlinetime.setPrefix("§8× §7" + hours + " §eSt");
        p.setScoreboard(sb);
        Bukkit.getScheduler().runTaskTimerAsynchronously(BuildFFA.getPlugin(), () -> {
            coins.setPrefix("§8× §7"+ BaumAPI.getCoinsAPI().getCoins(p));
            onlinetime.setPrefix("§8× §7" + ""+ " Level");
        },0,20);
        Team Leitung = sb.registerNewTeam("000Leitung");
        Team Administrator = sb.registerNewTeam("001Administrator");
        Team Manager = sb.registerNewTeam("002Manager");
        Team SrDev = sb.registerNewTeam("003SrDev");
        Team SrContent = sb.registerNewTeam("004SrContent");
        Team SrBuilder = sb.registerNewTeam("005SrBuilder");
        Team SrSupporter = sb.registerNewTeam("006SrSupporter");
        Team Dev = sb.registerNewTeam("007Dev");
        Team Content = sb.registerNewTeam("008Content");
        Team Builder = sb.registerNewTeam("009Builder");
        Team Supporter = sb.registerNewTeam("010Supporter");
        Team JrDev = sb.registerNewTeam("011JrDev");
        Team JrContent = sb.registerNewTeam("012JrContent");
        Team JrBuilder = sb.registerNewTeam("013JrBuilder");
        Team JrSupporter = sb.registerNewTeam("014JrSupporter");
        Team Freund = sb.registerNewTeam("015Freund");
        Team Creator = sb.registerNewTeam("016Creator");
        Team Partner = sb.registerNewTeam("017Partner");
        Team Baum = sb.registerNewTeam("018Baum");
        Team Prime = sb.registerNewTeam("019Prime");
        Team Premium = sb.registerNewTeam("020Premium");
        Team Spieler = sb.registerNewTeam("021Spieler");
        Leitung.setPrefix("§fL §8◆ §7");
        Administrator.setPrefix("§4A §8◆ §7");
        Manager.setPrefix("§cM §8◆ §7");
        SrDev.setPrefix("§9SrD §8◆ §7");
        SrContent.setPrefix("§aSrC §8◆ §7");
        SrBuilder.setPrefix("§eSrB §8◆ §7");
        SrSupporter.setPrefix("§bSrS §8◆ §7");
        Dev.setPrefix("§9D §8◆ §7");
        Content.setPrefix("§aC §8◆ §7");
        Builder.setPrefix("§eB §8◆ §7");
        Supporter.setPrefix("§bS §8◆ §7");
        JrDev.setPrefix("§9JrD §8◆ §7");
        JrContent.setPrefix("§aJrC §8◆ §7");
        JrBuilder.setPrefix("§eJrB §8◆ §7");
        JrSupporter.setPrefix("§bJrS §8◆ §7");
        Freund.setPrefix("§2F §8◆ §7");
        Creator.setPrefix("§5C §8◆ §7");
        Partner.setPrefix("§3P §8◆ §7");
        Baum.setPrefix("§2B §8◆ §7");
        Prime.setPrefix("§dP §8◆ §7");
        Premium.setPrefix("§6P §8◆ §7");
        Spieler.setPrefix("§7");
        Bukkit.getOnlinePlayers().forEach(all -> {
            IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(all.getUniqueId());
            if(permissionPlayer.hasPermissionGroup("Leitung")){
                Leitung.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Administrator")){
                Administrator.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Manager")){
                Manager.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrDev")){
                SrDev.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrContent")){
                SrContent.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrBuilder")){
                SrBuilder.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrSupporter")){
                SrSupporter.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Dev")){
                Dev.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Content")){
                Content.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Builder")){
                Builder.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Supporter")){
                Supporter.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrDev")){
                JrDev.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrContent")){
                JrContent.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrBuilder")){
                JrBuilder.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrSupporter")){
                JrSupporter.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Freund")){
                Freund.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Creator")){
                Creator.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Partner")){
                Partner.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Baum")){
                Baum.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Prime")){
                Prime.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Premium")){
                Premium.addEntry(all.getName());
            }
            if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
                Spieler.addEntry(all.getName());
            }
        });

    }
    public static void updateTab(Player p) {
        Scoreboard sb = p.getScoreboard();
        Team Leitung = sb.getTeam("000Leitung");
        Team Administrator = sb.getTeam("001Administrator");
        Team Manager = sb.getTeam("002Manager");
        Team SrDev = sb.getTeam("003SrDev");
        Team SrContent = sb.getTeam("004SrContent");
        Team SrBuilder = sb.getTeam("005SrBuilder");
        Team SrSupporter = sb.getTeam("006SrSupporter");
        Team Dev = sb.getTeam("007Dev");
        Team Content = sb.getTeam("008Content");
        Team Builder = sb.getTeam("009Builder");
        Team Supporter = sb.getTeam("010Supporter");
        Team JrDev = sb.getTeam("011JrDev");
        Team JrContent = sb.getTeam("012JrContent");
        Team JrBuilder = sb.getTeam("013JrBuilder");
        Team JrSupporter = sb.getTeam("014JrSupporter");
        Team Freund = sb.getTeam("015Freund");
        Team Creator = sb.getTeam("016Creator");
        Team Partner = sb.getTeam("017Partner");
        Team Baum = sb.getTeam("018Baum");
        Team Prime = sb.getTeam("019Prime");
        Team Premium = sb.getTeam("020Premium");
        Team Spieler = sb.getTeam("021Spieler");
        Bukkit.getOnlinePlayers().forEach(all -> {
            if(Spieler == null ||Freund == null || Creator == null || Partner  == null || Baum == null || Prime == null || Premium == null ||JrContent == null || JrBuilder == null || JrSupporter == null ||SrSupporter == null || Dev == null || Content == null|| Builder == null || Supporter == null || JrDev == null ||Leitung == null || Administrator == null || Manager == null || SrDev == null || SrContent == null || SrBuilder == null || SrBuilder == null) {
            }
            IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(all.getUniqueId());
            if(permissionPlayer.hasPermissionGroup("Leitung")){
                assert Leitung != null;
                Leitung.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Administrator")){
                assert Administrator != null;
                Administrator.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Manager")){
                assert Manager != null;
                Manager.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrDev")){
                assert SrDev != null;
                SrDev.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrContent")){
                assert SrContent != null;
                SrContent.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrBuilder")){
                assert SrBuilder != null;
                SrBuilder.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("SrSupporter")){
                assert SrSupporter != null;
                SrSupporter.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Dev")){
                assert Dev != null;
                Dev.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Content")){
                assert Content != null;
                Content.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Builder")){
                assert Builder != null;
                Builder.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Supporter")){
                assert Supporter != null;
                Supporter.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrDev")){
                assert JrDev != null;
                JrDev.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrContent")){
                assert JrContent != null;
                JrContent.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrBuilder")){
                assert JrBuilder != null;
                JrBuilder.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("JrSupporter")){
                assert JrSupporter != null;
                JrSupporter.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Freund")){
                assert Freund != null;
                Freund.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Creator")){
                assert Creator != null;
                Creator.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Partner")){
                assert Partner != null;
                Partner.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Baum")){
                assert Baum != null;
                Baum.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Prime")){
                assert Prime != null;
                Prime.addEntry(all.getName());
            } else if(permissionPlayer.hasPermissionGroup("Premium")){
                assert Premium != null;
                Premium.addEntry(all.getName());
            }
            if(permissionPlayer.getPermissionGroupInfoList().size() == 1) {
                assert Spieler != null;
                Spieler.addEntry(all.getName());
            }
        });
    }

}
