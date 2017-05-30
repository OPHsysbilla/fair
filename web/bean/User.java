package bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;
import database.MyEnviroment;

public class User{
	private int id;
	private String username;
	private String password;
	private int authority;
	private final static String HEADPORTRAITFILENAME = "head_portrait.png";
	private String absolutelyHeadPortraitPath;
	private String relativeHeadPortraitPath;
	
	
	public void setAbsolutelyHeadPortraitPath(String absolutelyHeadPortraitPath) {
		this.absolutelyHeadPortraitPath = absolutelyHeadPortraitPath;
	}
	
	
	public void setRelativeHeadPortraitPath(String relativeHeadPortraitPath) {
		this.relativeHeadPortraitPath = relativeHeadPortraitPath;
	}
	
	
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
				user.absolutelyHeadPortraitPath = user.getAbsolutelyHeadPortraitPath();
				user.relativeHeadPortraitPath = user.getRelativeHeadPortraitPath();
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
				user.absolutelyHeadPortraitPath = user.getAbsolutelyHeadPortraitPath();
				user.relativeHeadPortraitPath = user.getRelativeHeadPortraitPath();
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
				user.absolutelyHeadPortraitPath = user.getAbsolutelyHeadPortraitPath();
				user.relativeHeadPortraitPath = user.getRelativeHeadPortraitPath();
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
	
	
	public static boolean addUser(User user, boolean isHeadProtraitUploaded)
	{
		String sqlInsert = "insert into user_table values(" 
				+ "NULL" + ",'" 
				+ user.getUsername() + "','" 
				+ user.getPassword() + "','"
				+ 0 + "')";
		boolean result = DBQueryHelper.executeUpdate(sqlInsert);
		if(!result)
		{
			return result;
		}
		File mainPath = new File(MyEnviroment.USERSPATH + user.getUsername());
		mainPath.mkdir();
		File filesPath = new File(MyEnviroment.USERSPATH + user.getUsername() + "/files");
		filesPath.mkdir();
		if(!isHeadProtraitUploaded)
		{
			try {
				RandomAccessFile originalRaf = new RandomAccessFile(MyEnviroment.ROOTPATH
						 + "default_headportrait.png", "r");
				FileOutputStream targetFos = new FileOutputStream(user.getAbsolutelyHeadPortraitPath(), false);
				int size = 0;
				byte[] buffer = new byte[1048576];
				while( (size = originalRaf.read(buffer, 0, 1048576)) != -1)
				{
					targetFos.write(buffer, 0, size);
				}
				targetFos.close();
				originalRaf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
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
		return MyEnviroment.USERSPATH + this.getUsername();
	}
	
	
	public String getRelativeHeadPortraitPath()
	{
		//return this.getMainDirPath() + "/" + HEADPORTRAITFILENAME;
		return "/users/" + this.username + "/" + "head_portrait.png";
	}
	
	
	public String getAbsolutelyHeadPortraitPath()
	{
		return this.getMainDirPath() + "/" + HEADPORTRAITFILENAME;
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
