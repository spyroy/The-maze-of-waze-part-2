package gameClient;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.HashSet;

public class Data_base 
{
	//Database credentials
	public static final String jdbcUrl="jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	public static final String jdbcUser="student";
	public static final String jdbcUserPassword="OOP2020student";
	public static HashSet<Integer> levelId = new HashSet<>();
	public static HashSet<Integer> id = new HashSet<>();
	public static HashSet<Integer> moves = new HashSet<>();
	public static HashSet<Integer> score = new HashSet<>();
	public static HashSet<Date> dateTime = new HashSet<>();
	public static int DBsize=0;
	
	/** simply prints all the games as played by the users (in the database).
	 * 
	 */
	public static void printLog() 
	{
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");//Register JDBC driver
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			String allCustomersQuery = "SELECT * FROM Logs;";
			statement.execute(allCustomersQuery);
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			int[] minMove = new int[24];
			for (int i = 0; i < minMove.length; i++) 
			{
				minMove[i]=1040;
			}
			minMove[0] = 290;
			minMove[1] = 580;
			minMove[3] = 580;
			minMove[5] = 500;
			minMove[9] = 580;
			minMove[11] = 580;
			minMove[13] = 580;
			minMove[16] = 290;
			minMove[19] = 580;
			minMove[20] = 290;
			minMove[23] = 1140;
			int[] maxScore = new int[24];
			boolean[] level = new boolean[24];

			while(resultSet.next())
			{
				//System.out.println("Id: " + resultSet.getInt("UserID")+","+resultSet.getInt("levelID")+","+resultSet.getInt("moves")+","+resultSet.getDate("time"));
				//System.out.println("Id: " + resultSet.getInt("UserID")+","+resultSet.getInt("levelID")+","+resultSet.getInt("moves")+","+resultSet.getDate("time")+", "+resultSet.getInt("score"));
				if(resultSet.getInt("UserID")==315524389&&minMove[resultSet.getInt("levelID")]>=resultSet.getInt("moves")&&maxScore[resultSet.getInt("levelID")]<=resultSet.getInt("score")||(resultSet.getInt("levelID")>=0&&resultSet.getInt("UserID")==315524389&&resultSet.getInt("levelID")<24&&level[resultSet.getInt("levelID")]==false)){
					id.add(resultSet.getInt("UserID"));
					levelId.add(resultSet.getInt("levelID"));
					score.add(resultSet.getInt("score"));
					level[resultSet.getInt("levelID")] = true;
					moves.add(resultSet.getInt("moves"));
					dateTime.add(resultSet.getDate("time"));
					maxScore[resultSet.getInt("levelID")] = resultSet.getInt("score");
					DBsize++;
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		}

		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static int allUsers()
	{
		int ans = 0;
		String allCustomersQuery = "SELECT * FROM Users;";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);		
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			while(resultSet.next()) {
				System.out.println("Id: " + resultSet.getInt("UserID")+", max_level:"+resultSet.getInt("levelNum"));
				ans++;
			}
			resultSet.close();
			statement.close();		
			connection.close();
		}
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}

	public static void main(String[] args) {
		int id1 = 999;
		int level = 0;

		//allUsers();
		printLog();

		
	}
	

}