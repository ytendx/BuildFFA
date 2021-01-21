package net.minebaum.buildffa.commands;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.utils.mysql.SQLStats;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        Player p = (Player) s;
        if(args.length == 0) {
            Integer kills = SQLStats.getKills(p.getUniqueId().toString());
            Integer deaths = SQLStats.getDeaths(p.getUniqueId().toString());
            double KD = ((double) kills) / ((double)  deaths);
            String kd = Double.valueOf(KD).toString();
            p.sendMessage("        §7BuildFFA §8× §cSTATS     ");
            p.sendMessage(" ");
            p.sendMessage("§7  Points §8● §7" + SQLStats.getPoints(p.getUniqueId().toString()));
            p.sendMessage("§7  Kills §8● §7" + kills);
            p.sendMessage("§7  Tode §8● §7" + deaths);
            if(kd.length() <= 5) {
                p.sendMessage("§7  KD §8● §7" + kd.replace("Infinity","0").replace("NaN","0"));
            } else {
                p.sendMessage("§7  KD §8● §7" + kd.substring(0,4).replace("Infinity","0").replace("NaN","0"));
            }
            p.sendMessage(" ");
            p.sendMessage("        §7BuildFFA §8× §cSTATS     ");
        }else if(args.length == 1){
            Player t = Bukkit.getPlayer(args[0]);
            if(t == null){
                p.sendMessage(Data.PREFIX + "§cDer Spieler " + t.getName() + " ist nicht online!");
                return true;
            }
            Integer kills = SQLStats.getKills(t.getUniqueId().toString());
            Integer deaths = SQLStats.getDeaths(t.getUniqueId().toString());
            double KD = ((double) kills) / ((double)  deaths);
            String kd = Double.valueOf(KD).toString();
            p.sendMessage("        §7BuildFFA §8× §cSTATS von" + t.getName() + "    ");
            p.sendMessage(" ");
            p.sendMessage("§7  Points §8● §7" + SQLStats.getPoints(t.getUniqueId().toString()));
            p.sendMessage("§7  Kills §8● §7" + kills);
            p.sendMessage("§7  Tode §8● §7" + deaths);
            if(kd.length() <= 5) {
                p.sendMessage("§7  KD §8● §7" + kd.replace("Infinity","0").replace("NaN","0"));
            } else {
                p.sendMessage("§7  KD §8● §7" + kd.substring(0,4).replace("Infinity","0").replace("NaN","0"));
            }
            p.sendMessage(" ");
            p.sendMessage("        §7BuildFFA §8× §cSTATS     ");
        } else{
            p.sendMessage(Data.NOPERMS);
        }
        return false;
    }
}
