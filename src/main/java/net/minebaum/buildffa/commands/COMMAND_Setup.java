package net.minebaum.buildffa.commands;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Locale;

public class COMMAND_Setup implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(!(commandSender instanceof Player)) {return true;}
        if(strings.length == 0 || strings.length > 1) {p.sendMessage(Data.PREFIX + "§cNutze: /setup <Location>"); return true;}
        if(!(p.hasPermission("system.setup"))) {p.sendMessage(Data.NOPERMS); return true;}
        try {
            LocationManager.setLocation(strings[0].toLowerCase(), p.getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
