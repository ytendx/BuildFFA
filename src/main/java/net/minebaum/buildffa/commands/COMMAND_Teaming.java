package net.minebaum.buildffa.commands;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.GameManagement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_Teaming implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        final Player p = (Player) commandSender;
        if(!(commandSender instanceof Player)) {return true;}
        if(args.length != 0) {p.sendMessage(Data.PREFIX + "§cNutze: /teaming"); return true;}
        if(!(p.hasPermission("system.teaming"))) {p.sendMessage(Data.NOPERMS); return true;}
        if(GameManagement.teaming){
            GameManagement.teaming = false;
        }else GameManagement.teaming = true;
        p.sendMessage(Data.PREFIX + "§eDas Teaming wurde auf " + Boolean.toString(GameManagement.teaming) + " gesetzt.");
        return false;
    }
}
