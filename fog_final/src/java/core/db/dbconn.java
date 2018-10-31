package core.db;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class dbconn {

    static Connection connection = null;
    static Statement statement = null;
    static PreparedStatement prestmt = null;
    static String url = "jdbc:mysql://localhost:3306/";
    static String dbName = "fogcomputing_privacy";
    static String driver = "com.mysql.jdbc.Driver";
    static String userName = "root";
    static String password = "";

    public static Statement connect() throws Exception {

        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url + dbName, userName, password);
            statement = connection.createStatement();


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }
        return statement;
    }

    public static Connection getConn() throws Exception {

        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url + dbName, userName, password);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
