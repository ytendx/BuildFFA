package net.minebaum.buildffa.commands;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.utils.spectators.SpecHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_Spec implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)){return true;}
        final Player p = (((Player) commandSender).getPlayer());
        if(args.length > 1){p.sendMessage(Data.PREFIX + "§cNutze: /spec oder /spec <Name>"); return true;}
        if(!p.hasPermission("system.spec")){p.sendMessage(Data.NOPERMS); return true;}
        if(args.length == 0){
            SpecHandler.setSpecs(p);
        }else{
            SpecHandler.setSpecs(p);
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                p.teleport(target);
            }
        }
        return false;
    }
}
