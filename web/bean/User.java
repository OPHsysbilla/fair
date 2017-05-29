package bean;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;

public class User{
	private int id;
	private String username;
	private String password;
	private int authority;
	
	
	public User()
	{
		this.authority = 0;
	}
	
	
	public static User getUserByID(int ID)
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from user_table where id = '" + ID + "'");
		User user = null;
		try {
			if(resultSet.next())
			{
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setAuthority(resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return user;
	}
	
	
	
	public static User[] getAllUsers()
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from user_table");
		ArrayList<User> users = new ArrayList<User>();
  		try {
			while(resultSet.next())
			{
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setAuthority(resultSet.getInt(4));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (User[])users.toArray(new User[users.size()]);
	}
	
	
	public static User getUserByName(String username)
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from user_table where username = '" + username + "'");
		User user = null;
		try {
			if(resultSet.next())
			{
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setAuthority(resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return user;
	}
	
	
	public static boolean deleteUser(int ID)
	{
		String sqlDelete = "delete from user_table where id = '" + ID + "'";
		return DBQueryHelper.execute(sqlDelete);
	}
	
	
	public static boolean addUser(User user)
	{
		String sqlInsert = "insert into user_table values(" 
				+ "NULL" + ",'" 
				+ user.getUsername() + "','" 
				+ user.getPassword() + "','"
				+ 0 + "')";
		File mainPath = new File("E:/mBlogDB/users/" + user.getUsername());
		mainPath.mkdir();
		File filesPath = new File("E:/mBlogDB/users/" + user.getUsername() + "/files");
		filesPath.mkdir();
		return DBQueryHelper.executeUpdate(sqlInsert);
	}
	
	
	public void setUsername(String value)
	{
		if(value == null)
			this.username = "缺省";
		this.username = value;
	}
	
	
	public String getUsername()
	{
		return this.username;
	}
	
	
	public void setPassword(String value)
	{
		if(value == null)
			this.password = "123456";
		this.password = value;
	}
	
	
	public String getPassword()
	{
		return this.password;
	}
	
	
	public void setId(int value)
	{
		this.id = value;
	}
	
	
	public int getId()
	{
		return this.id;
	}
	
	
	public int getAuthority()
	{
		return this.authority;
	}
	
	
	public String getMainDirPath()
	{
		return "E:/mBlogDB/users/" + this.getUsername();
	}
	
	
	public String getHeadPortraitPath()
	{
		return this.getMainDirPath() + "/head_portrait.png";
	}
	
	
	public String getFilesDir()
	{
		return this.getMainDirPath() + "/files";
	}
	
	public void setAuthority(int value)
	{
		this.authority = value;
	}
}
