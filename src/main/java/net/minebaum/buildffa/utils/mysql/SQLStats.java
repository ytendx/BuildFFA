package net.minebaum.buildffa.utils.mysql;



import net.minebaum.buildffa.GameManagement;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLStats {
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
    public static Integer getPoints(String uuid) {
        Integer i = 0;
        if (playerExists(uuid)) {
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
