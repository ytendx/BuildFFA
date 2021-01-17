package net.minebaum.buildffa.utils;

import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
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
        Team Leitung = sb.registerNewTeam("000");
        Team Administrator = sb.registerNewTeam("001");
        Team Manager = sb.registerNewTeam("002");
        Team SrDev = sb.registerNewTeam("003");
        Team SrContent = sb.registerNewTeam("004");
        Team SrBuilder = sb.registerNewTeam("005");
        Team SrSupporter = sb.registerNewTeam("006");
        Team Dev = sb.registerNewTeam("007");
        Team Content = sb.registerNewTeam("008");
        Team Builder = sb.registerNewTeam("009");
        Team Supporter = sb.registerNewTeam("010");
        Team JrDev = sb.registerNewTeam("011");
        Team JrContent = sb.registerNewTeam("012");
        Team JrBuilder = sb.registerNewTeam("013");
        Team JrSupporter = sb.registerNewTeam("014");
        Team Freund = sb.registerNewTeam("015");
        Team Creator = sb.registerNewTeam("016");
        Team Partner = sb.registerNewTeam("017");
        Team Baum = sb.registerNewTeam("018");
        Team Prime = sb.registerNewTeam("019");
        Team Premium = sb.registerNewTeam("020");
        Team Spieler = sb.registerNewTeam("021");
        Leitung.setPrefix("§fLeitung §8◆ §7");
        Administrator.setPrefix("§4Admin §8◆ §7");
        Manager.setPrefix("§cManager §8◆ §7");
        SrDev.setPrefix("§9SrDev §8◆ §7");
        SrContent.setPrefix("§aSrCont §8◆ §7");
        SrBuilder.setPrefix("§eSrBuild §8◆ §7");
        SrSupporter.setPrefix("§bSrSupp §8◆ §7");
        Dev.setPrefix("§9Dev §8◆ §7");
        Content.setPrefix("§aContent §8◆ §7");
        Builder.setPrefix("§eBuild §8◆ §7");
        Supporter.setPrefix("§bSupp §8◆ §7");
        JrDev.setPrefix("§9JrDev §8◆ §7");
        JrContent.setPrefix("§aJrCont §8◆ §7");
        JrBuilder.setPrefix("§eJrBuild §8◆ §7");
        JrSupporter.setPrefix("§bJrSupp §8◆ §7");
        Freund.setPrefix("§2Freund §8◆ §7");
        Creator.setPrefix("§5Creator §8◆ §7");
        Partner.setPrefix("§3Partner §8◆ §7");
        Baum.setPrefix("§2BAUM §8◆ §7");
        Prime.setPrefix("§dPrime §8◆ §7");
        Premium.setPrefix("§6Prem §8◆ §7");
        Spieler.setPrefix("§7");

        Leitung.setSuffix("  ");
        Administrator.setSuffix("  ");
        Manager.setSuffix("  ");
        SrDev.setSuffix("  ");
        SrContent.setSuffix("  ");
        SrBuilder.setSuffix("  ");
        SrSupporter.setSuffix("  ");
        Dev.setSuffix("  ");
        Content.setSuffix("  ");
        Builder.setSuffix("  ");
        Supporter.setSuffix("  ");
        JrDev.setSuffix("  ");
        JrContent.setSuffix("  ");
        JrBuilder.setSuffix("  ");
        JrSupporter.setSuffix("  ");
        Freund.setSuffix("  ");
        Creator.setSuffix("  ");
        Partner.setSuffix("  ");
        Baum.setSuffix("  ");
        Prime.setSuffix("  ");
        Premium.setSuffix("  ");
        Spieler.setSuffix("  ");
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
            if(Spieler == null ||Freund == null || Creator == null || Partner  == null || Baum == null || Prime == null || Premium == null ||JrContent == null || JrBuilder == null || JrSupporter == null ||SrSupporter == null || Dev == null || Content == null|| Builder == null || Supporter == null || JrDev == null ||Leitung == null || Administrator == null || Manager == null || SrDev == null || SrContent == null || SrBuilder == null || SrBuilder == null) {
            }
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
    public static void updateTab(Player p) {
        Scoreboard sb = p.getScoreboard();
        Team Leitung = sb.registerNewTeam("000");
        Team Administrator = sb.registerNewTeam("001");
        Team Manager = sb.registerNewTeam("002");
        Team SrDev = sb.registerNewTeam("003");
        Team SrContent = sb.registerNewTeam("004");
        Team SrBuilder = sb.registerNewTeam("005");
        Team SrSupporter = sb.registerNewTeam("006");
        Team Dev = sb.registerNewTeam("007");
        Team Content = sb.registerNewTeam("008");
        Team Builder = sb.registerNewTeam("009");
        Team Supporter = sb.registerNewTeam("010");
        Team JrDev = sb.registerNewTeam("011");
        Team JrContent = sb.registerNewTeam("012");
        Team JrBuilder = sb.registerNewTeam("013");
        Team JrSupporter = sb.registerNewTeam("014");
        Team Freund = sb.registerNewTeam("015");
        Team Creator = sb.registerNewTeam("016");
        Team Partner = sb.registerNewTeam("017");
        Team Baum = sb.registerNewTeam("018");
        Team Prime = sb.registerNewTeam("019");
        Team Premium = sb.registerNewTeam("020");
        Team Spieler = sb.registerNewTeam("021");
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
