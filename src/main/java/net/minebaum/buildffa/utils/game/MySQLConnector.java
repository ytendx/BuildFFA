package net.minebaum.buildffa.utils.game;

import net.minebaum.baumapi.BaumAPI;
import java.sql.*;

public class MySQLConnector {
    private String host;
    private String database;
    private String user;
    private String password;
    private int port;
    private Connection connection;

    public MySQLConnector(String host, Integer port, String database, String user, String password){
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
        connect();
    }

    public boolean isInDatabase(String username){
        boolean exist = false;
        try {
            ResultSet rs =
                    query("SELECT username FROM guiaccounts WHERE username='" +
                            username + "';");
            while (rs.next())
                exist = Boolean.valueOf(true).booleanValue();
        } catch (Exception err) {
            exist = false;
        }
        return exist;
    }

    public void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + host +
                    ":" + port + "/" + database, user, password);
            BaumAPI.getPlugin().sCMSG("MySQL Connection established!");
        } catch (SQLException e) {
            BaumAPI.getPlugin().sCMSG("§cMistake: Connection cannot be established!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(String qry) {
        try {
            Statement stmt = (Statement) connection.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (Exception ex) {
            connect();
            System.err.println(ex);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement stmt = (Statement) connection.createStatement();
            rs = stmt.executeQuery(qry);
        } catch (Exception ex) {
            connect();
            System.err.println(ex);
        }
        return rs;
    }


}
