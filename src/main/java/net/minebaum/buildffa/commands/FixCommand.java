package net.minebaum.buildffa.commands;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.BuildFFA;
import net.minebaum.buildffa.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final Player p = (Player) commandSender;
        if(commandSender == null) {return true;}
        if(strings.length != 0) {p.sendMessage(Data.PREFIX + "§cNutze: /fix"); return true;}
        Location from = p.getLocation();
        p.teleport(LocationManager.getLocation("spawn"));
        Bukkit.getScheduler().runTaskLater(BuildFFA.getPlugin(), new Runnable() {
            @Override
            public void run() {
                p.teleport(from);
                p.sendMessage(Data.PREFIX + "§eMap Fixed!");
            }
        }, 2);
        return false;
    }
}
