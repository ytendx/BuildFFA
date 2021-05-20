package net.minebaum.buildffa.utils;

import net.minebaum.baumapi.utils.Data;
import net.minebaum.buildffa.BuildFFA;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class EventHandler {

    private static int currentEvent = -1;

    public static int getCurrentEvent() {
        return currentEvent;
    }

    public static void setCurrentEvent(int currentEvent) {
        EventHandler.currentEvent = currentEvent;
    }

    public static final int SLOWNESS = 0,
                            JUMP_SPEED = 1,
                            ALL_KITS = 2;

    public static boolean allKits = false;

    public EventHandler(){
        Bukkit.getScheduler().runTaskTimer(BuildFFA.getPlugin(), new Runnable() {
            @Override
            public void run() {
                startEvent();
            }
        }, 0, 20*60*15);
    }

    public static void startEvent(){
        Random r = new Random();
        int currentJob = r.nextInt(3);
        if(currentJob == SLOWNESS){
            for(Player all : Bukkit.getOnlinePlayers()){
                all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 2));
                all.sendMessage(Data.PREFIX + "§8███████████████████████");
                all.sendMessage(Data.PREFIX +"§7Das Event ist gestartet!");
                all.sendMessage(Data.PREFIX +"§7Typ: §eLangsamkeit");
                all.sendMessage(Data.PREFIX +"§cDas Event endet in 3 Minuten.");
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
            }
            currentEvent = SLOWNESS;
        }else if(currentJob == JUMP_SPEED){
            for(Player all : Bukkit.getOnlinePlayers()){
                all.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, 2));
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
                all.sendMessage(Data.PREFIX +"§7Das Event ist gestartet!");
                all.sendMessage(Data.PREFIX +"§7Typ: §eJumpEffect");
                all.sendMessage(Data.PREFIX +"§cDas Event endet in 3 Minuten.");
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
            }
            currentEvent = JUMP_SPEED;
        }else if(currentJob == ALL_KITS){
            for(Player all : Bukkit.getOnlinePlayers()){
                allKits = true;
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
                all.sendMessage(Data.PREFIX +"§7Das Event ist gestartet!");
                all.sendMessage(Data.PREFIX +"§7Typ: §eAlle Kits im Besitz");
                all.sendMessage(Data.PREFIX +"§cDas Event endet in 3 Minuten.");
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
            }
            currentEvent = ALL_KITS;
        }else{
            for(Player all : Bukkit.getOnlinePlayers()){
                all.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, 2));
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
                all.sendMessage(Data.PREFIX +"§7Das Event ist gestartet!");
                all.sendMessage(Data.PREFIX +"§7Typ: §eJumpEffect");
                all.sendMessage(Data.PREFIX +"§cDas Event endet in 3 Minuten.");
                all.sendMessage(Data.PREFIX +"§8███████████████████████");
            }
            currentEvent = JUMP_SPEED;
        }
        Bukkit.getScheduler().runTaskLater(BuildFFA.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.sendMessage(Data.PREFIX +"§8███████████████████████");
                    all.sendMessage(Data.PREFIX +"§7Das Event ist nun zuende!");
                    all.sendMessage(Data.PREFIX +"§8███████████████████████");
                    all.removePotionEffect(PotionEffectType.JUMP);
                    all.removePotionEffect(PotionEffectType.SLOW);
                }
                allKits = false;
                currentEvent = -1;
            }
        }, 20*60*3);

    }

}
