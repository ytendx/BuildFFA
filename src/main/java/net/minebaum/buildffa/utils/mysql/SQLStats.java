package net.minebaum.buildffa.utils.mysql;



import net.minebaum.baumapi.api.GuiAPI;
import net.minebaum.baumapi.utils.ItemBuilder;
import net.minebaum.buildffa.GameManagement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class SQLStats {

    public static final HashMap<String, Integer> CACHE_KILLS = new HashMap<>();
    public static final HashMap<String, Integer> CACHE_DEATHS = new HashMap<>();
    public static final HashMap<String, Integer> CACHE_POINTS = new HashMap<>();

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = GameManagement.getConnector().query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if (!(playerExists(uuid))) {
            GameManagement.getConnector().update("INSERT INTO Stats(UUID,KILLS,DEATHS,POINTS) VALUES ('" + uuid + "' ,'0','0','3');");
        }
    }

    public static Integer getKills(String uuid) {
        Integer i = 0;
        if(CACHE_KILLS.containsKey(uuid))
            return CACHE_KILLS.get(uuid);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = GameManagement.getConnector().query("Select * FROM Stats WHERE UUID= '" + uuid + "'");
                if ((!rs.next())) {
                } else {
                    rs.getInt("KILLS");
                }
                i = rs.getInt("KILLS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static void setKills(String uuid, Integer kills) {
        if (playerExists(uuid)) {
            /*if(CACHE.containsKey(uuid)){
                CACHE.replace(uuid, CACHE.get(uuid));
                CACHE.get(uuid).set(KILLS, kills);
            }else{
                CACHE.put(uuid, new ArrayList());
                CACHE.get(uuid).set(KILLS, kills);
            }*/
            GameManagement.getConnector().update("UPDATE Stats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "'");
        } else {
            createPlayer(uuid);
            setKills(uuid, kills);
        }
    }
    public static void addKills(String uuid, Integer kills) {
        if (playerExists(uuid)) {
            setKills(uuid,getKills(uuid) + kills);
        } else {
            createPlayer(uuid);
            addKills(uuid, kills);
        }
    }
    public static void removeKills(String uuid, Integer kills) {
        if (playerExists(uuid)) {
            setKills(uuid,getKills(uuid) - kills);
        } else {
            createPlayer(uuid);
            removeKills(uuid, kills);
        }
    }
    public static Integer getDeaths(String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            //if(CACHE.containsKey(uuid))
                //return (Integer) CACHE.get(uuid).get(DEATHS);
            try {
                ResultSet rs = GameManagement.getConnector().query("Select * FROM Stats WHERE UUID= '" + uuid + "'");
                if ((!rs.next())) {
                    ;
                } else {
                    rs.getInt("DEATHS");
                }
                i = rs.getInt("DEATHS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }

    public static void setDeaths(String uuid, Integer deaths) {
        if (playerExists(uuid)) {
            /*if(CACHE.containsKey(uuid)){
                CACHE.replace(uuid, CACHE.get(uuid));
                CACHE.get(uuid).set(DEATHS, deaths);
            }else{
                CACHE.put(uuid, new ArrayList());
                CACHE.get(uuid).set(DEATHS, deaths);
            }*/
            GameManagement.getConnector().update("UPDATE Stats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "'");
        } else {
            createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }
    public static void addDeaths(String uuid, Integer deaths) {
        if (playerExists(uuid)) {
            setDeaths(uuid,getDeaths(uuid) + deaths);
        } else {
            createPlayer(uuid);
            addDeaths(uuid, deaths);
        }
    }
    public static void removeDeaths(String uuid, Integer deaths) {
        if (playerExists(uuid)) {
            setDeaths(uuid,getDeaths(uuid) - deaths);
        } else {
            createPlayer(uuid);
            removeDeaths(uuid, deaths);
        }
    }

    public static void openStatsHead(Player p) {
        Inventory inv = new GuiAPI().fillerGUI(9,new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short)15).setDisplayname(" ").build(),"§7GunGame §8× §cSTATS");
        ArrayList<String> stats = new ArrayList<>();
        Integer kills = SQLStats.getKills(p.getUniqueId().toString());
        Integer deaths = SQLStats.getDeaths(p.getUniqueId().toString());
        double KD = ((double) kills) / ((double)  deaths);
        String kd = Double.valueOf(KD).toString();
        stats.add("§7  Points §8● §7" + SQLStats.getPoints(p.getUniqueId().toString()));
        stats.add("§7  Kills §8● §7" + kills);
        stats.add("§7  Tode §8● §7" + deaths);
        if(kd.length() <= 5) {
            stats.add("§7  KD §8● §7" + kd.replace("Infinity","0").replace("NaN","0"));
        } else {
            stats.add("§7  KD §8● §7" + kd.substring(0,4).replace("Infinity","0").replace("NaN","0"));
        }
        ItemStack itemstats = new ItemBuilder(Material.NETHER_STAR,1,(short)0).setDisplayname("§7Deine Stats").setLore(stats).build();
        inv.setItem(4,itemstats);
        p.openInventory(inv);
    }


    public static Integer getPoints(String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
            //if(CACHE.containsKey(uuid))
            //    return (Integer) CACHE.get(uuid).get(POINTS);
            try {
                ResultSet rs = GameManagement.getConnector().query("Select * FROM Stats WHERE UUID= '" + uuid + "'");
                if ((!rs.next())) {
                    ;
                } else {
                    rs.getInt("POINTS");
                }
                i = rs.getInt("POINTS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getPoints(uuid);
        }
        return i;
    }
    public static void setPoints(String uuid, Integer points) {
        if (playerExists(uuid)) {
            /*if(CACHE.containsKey(uuid)){
                CACHE.replace(uuid, CACHE.get(uuid));
                CACHE.get(uuid).set(POINTS, points);
            }else{
                CACHE.put(uuid, new ArrayList());
                CACHE.get(uuid).set(POINTS, points);
            }*/
            GameManagement.getConnector().update("UPDATE Stats SET POINTS= '" + points + "' WHERE UUID= '" + uuid + "'");
        } else {
            createPlayer(uuid);
            setPoints(uuid, points);
        }
    }
    public static void addPoints(String uuid, Integer points) {
        if (playerExists(uuid)) {
            setPoints(uuid,getPoints(uuid) + points);
        } else {
            createPlayer(uuid);
            addPoints(uuid, points);
        }
    }
    public static void removePoints(String uuid, Integer points) {
        if (playerExists(uuid)) {
            setPoints(uuid,getPoints(uuid) - points);
        } else {
            createPlayer(uuid);
            removePoints(uuid, points);
        }
    }
}
