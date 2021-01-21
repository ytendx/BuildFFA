package net.minebaum.buildffa.utils;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import net.minebaum.baumapi.BaumAPI;
import net.minebaum.buildffa.GameManagement;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.concurrent.TimeUnit;

public class ScoreboardManager {

    public static void set(Player p) {
        ICloudPlayer player = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(p.getUniqueId());
        long millis = player.getOnlineTime();
        Integer hours = Math.toIntExact(TimeUnit.MILLISECONDS.toHours(millis));
        Scoreboard board = new Scoreboard();
        ScoreboardObjective objective = board.registerObjective("Test", IScoreboardCriteria.b);

        objective.setDisplayName("§c§lMineBaum §8| §7BuildFFA");
        board.setDisplaySlot(1, objective);

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(objective, 1);
        PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(objective,0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1,objective);

        sendPacket(p, removePacket);
        sendPacket(p, createPacket);
        sendPacket(p, display);
        ScoreboardTeam coins =  new ScoreboardTeam(board,"coins");
        coins.setPrefix("§8 | × §7" + BaumAPI.getCoinsAPI().getCoins(p));
        sendScore(p, board, objective, " ", 10);
        sendScore(p, board, objective, "§8 | §cProfil ", 9);
        sendScore(p, board, objective, "§8 | × §7" + p.getName(), 8);
        sendScore(p, board, objective, "  ", 7);
        sendScore(p, board, objective, "§8 | §aBäume ", 6);
        sendScore(p, board, objective, coins.getPrefix(), 5);
        sendScore(p, board, objective, "   ", 4);
        sendScore(p, board, objective, "§8 | §eKit ", 3);
        sendScore(p, board, objective, "§8 | × §7" + GameManagement.getMainSaver().get(p).getKit().getName(), 2);
        sendScore(p, board, objective, "    ", 1);
    }

    @SuppressWarnings("rawtypes")
    public static void sendPacket(Player p, Packet packet) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
    private static void sendScore(Player player ,Scoreboard board , ScoreboardObjective obj , String score , int scoreint) {
        ScoreboardScore s = new ScoreboardScore(board,obj,score);
        s.setScore(scoreint);
        sendPacket(player ,new PacketPlayOutScoreboardScore(s));
    }


}
