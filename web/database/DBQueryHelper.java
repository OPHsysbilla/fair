package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DBQueryHelper {
	private static Connection connection;
	private static Statement statement;
	private static ResultSet rs;
	
	private static void connectDB()
	{
  		try {
			DriverManager.registerDriver(new Driver());
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mblog", "root", "123456");
			statement = connection.createStatement();
  		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ResultSet executeQuery(String query)
	{
		if(DBQueryHelper.isClosed())
		{
			DBQueryHelper.connectDB();
		}
		try {
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	
	public static boolean executeUpdate(String update)
	{
		if(DBQueryHelper.isClosed())
		{
			DBQueryHelper.connectDB();
		}
		try {
			statement.executeUpdate(update);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static boolean execute(String exec)
	{
		if(DBQueryHelper.isClosed())
		{
			DBQueryHelper.connectDB();
		}
		try {
			statement.execute(exec);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static void closeConnection()
	{
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static boolean isClosed()
	{
		if(statement == null || connection == null)
		{
			return true;
		}
		try {
			if(statement.isClosed())
			{
				return true;
			}
			else if(connection.isClosed())
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
