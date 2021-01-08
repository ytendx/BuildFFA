package net.minebaum.buildffa.utils.game;

import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class Stats {

    private StatsType[] types;
    private int typeLength;
    private Player player;
    private MySQLConnector mySQL;
    private Game game;

    public Stats(StatsType[] statsTypes, MySQLConnector mySQL, Game game) {
        this.types = statsTypes;
        typeLength = this.types.length-1;
        this.mySQL = mySQL;
        this.game = game;
    }

    public Stats(MySQLConnector mySQL, Game game) {
        this.mySQL = mySQL;
        this.game = game;
    }

    public int getStatsTypeLength() {
        return typeLength;
    }

    public StatsType[] getStatsTypeList() {
        return types;
    }

    public boolean hasStats(Player player) {
        return false;
    }

    public void setup() {
        String updateTypeSet = "";
        for(StatsType all : types) {
            switch (all) {
                case KD:
                    updateTypeSet += ", KD INTEGER";
                    break;
                case DEATHS:
                    updateTypeSet += ", DEATHS INTEGER";
                    break;
                case KILLS:
                    updateTypeSet += ", KILLS INTEGER";
                    break;
                case POINTS:
                    updateTypeSet += ", POINTS INTEGER";
                    break;
                case PLAYEDGAMES:
                    updateTypeSet += ", PLAYEDGAMES INTEGER";
                    break;
                case WONNEDROUNDS:
                    updateTypeSet += ", WONNEDROUNDS INTEGER";
                    break;
                default:
                    break;
            }
        }
        mySQL.update("CREATE TABLE IF NOT EXISTS stats" + game.getName().toLowerCase() + " (UUID VARCHAR(50)" + updateTypeSet + ")");
        game.sCMSG("Setuped Stats Database!");
    }



    public void createAccount(Player player) {
        boolean exist = false;
        StatsType current = null;
        for(StatsType all : types) {
            if(current != null) {
                current = all;
            }
        }
        try {
            ResultSet rs =
                    mySQL.query("SELECT " + current.toString().toUpperCase() + "FROM stats WHERE UUID='" +
                            player.getUniqueId().toString() + "';");
            while (rs.next())
                exist = Boolean.valueOf(true).booleanValue();
        } catch (Exception err) {
            System.err.println(err);
        }
        // GENERATE UPDATE STRING
        String updateTypeSeta = "";
        for(StatsType all : types) {
            switch (all) {
                case KD:
                    updateTypeSeta += ",KD";
                    break;
                case DEATHS:
                    updateTypeSeta += ",DEATHS";
                    break;
                case KILLS:
                    updateTypeSeta += ",KILLS";
                    break;
                case POINTS:
                    updateTypeSeta += ",POINTS";
                    break;
                case PLAYEDGAMES:
                    updateTypeSeta += ",PLAYEDGAMES";
                    break;
                case WONNEDROUNDS:
                    updateTypeSeta += ",WONNEDROUNDS";
                    break;
                default:
                    break;
            }
        }
        if (!exist) {
            mySQL.update("INSERT INTO stats (UUID," + String.valueOf(updateTypeSeta.toUpperCase()) + ") values ('" + player.getUniqueId().toString() + "', );");}
    }


}
