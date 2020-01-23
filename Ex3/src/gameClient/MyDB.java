package gameClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.ResultSetMetaData;

/**
 * This class represents a simple example of using MySQL Data-Base. Use this
 * example for writing solution.
 *
 * @author spyro
 *
 */
public class MyDB {
    public static final String jdbcUrl = "jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
    public static final String jdbcUser = "student";
    public static final String jdbcUserPassword = "OOP2020student";
    private static Connection con = null;
    private static Statement stat = null;

    /**
     * open a query to add all relevant elements
     * @param query
     * @return
     */
    private static ResultSet open(String query) {
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
            stat = con.createStatement();
            resultSet = stat.executeQuery(query);
        }

        catch (SQLException sqle) {
            System.out.println("Error");
            System.out.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    /**
     * close query
     * @param resultSet
     */
    private static void close(ResultSet resultSet) {
        try {
            resultSet.close();
            con.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /**
    * count the number of games played
    * @param id
    * @return
    */
    public static int NumOfGames(int id) {
        ResultSet resultSet = open("select * from Logs where userID = " + id);
        int num = 0;
        try {
            while (resultSet.next()) {
            	num++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(resultSet);
        return num;
    }

   /**
    * represent the personal best of all stages
    * @param id
    * @return
    */
    public static TreeMap<Integer, String> personalBest(int id) {
        String query = "SELECT * FROM Logs as logs inner join (";
        query+= "SELECT max(score) as score, levelID FROM Logs";
        query += " where userID = "+ id;
        query += " group by levelID";
        query += ") as groupedLogs";
        query += " on logs.levelID = groupedLogs.levelID and logs.score = groupedLogs.score";
        query += " where userID = " + id;
        query += " order by logs.levelID asc";
        ResultSet resultSet = open(query);
        TreeMap<Integer, String> ans= new TreeMap<Integer, String>();
        try {

            while (resultSet.next()) {
                String value = "" + resultSet.getInt("userID") + "," + resultSet.getInt("levelID") + ","
                        + resultSet.getInt("score") + "," + resultSet.getInt("moves") + "," + resultSet.getDate("time");
                ans.put(resultSet.getInt("levelID"), value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(resultSet);
        return ans;
    }

 

    /**
     * represent all users score
     * @param stage
     * @return
     */
    public static TreeMap<String, String> globalBest(int stage) {
        String query = "SELECT * FROM Logs as logs inner join (";
        query += "SELECT max(score) as score, levelID, userID FROM Logs group by levelID,userID";
        query += ") as groupedLogs";
        query += " on logs.userID = groupedLogs.userID and logs.levelID = groupedLogs.levelID and logs.score = groupedLogs.score";
        query += " where logs.levelID = " + stage; 
        query += " order by logs.score desc";
        ResultSet resultSet = open(query);
        TreeMap<String, String> ans = new TreeMap<String, String>();
        try {
            while (resultSet.next()) {
                String value = "" + resultSet.getInt("userID") + "," + resultSet.getInt("levelID") + ","
                        + resultSet.getInt("score") + "," + resultSet.getInt("moves") + "," + resultSet.getDate("time");
                ans.put(resultSet.getInt("userID") + "," + resultSet.getInt("levelID"), value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(resultSet);
        return ans;
    }
}