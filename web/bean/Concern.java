package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;

public class Concern {
	private int id;
	private int userId;
	private int friendId;
	private String time;
	
	
	public static boolean addConcern(Concern concern)
	{
		String sqlInsert = "insert into concern_table values(" 
				+ "NULL" + ",'" 
				+ concern.getUserId() + "','" 
				+ concern.getFriendId() + "',"
				+ "NOW()" + ")";
		return DBQueryHelper.executeUpdate(sqlInsert);
	}
	
	
	
	public static boolean deleteConcernById(int ID) //通过一个Concern关系的ID号来删除一个条目
	{
		String sqlDelete = "delete from concern_table where id = '" + ID + "'";
		return DBQueryHelper.execute(sqlDelete);
	}
	
	
	public static Concern[] getConcernsByUserId(int userID) //输入用户ID，获得这个用户名下的所有关注项目
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from concern_table where userId = '" + userID + "'");
		ArrayList<Concern> listConcerns = new ArrayList<Concern>();
  		try {
			while(resultSet.next())
			{
				Concern concern = new Concern();
				concern.setId(resultSet.getInt(1));
				concern.setUserId(resultSet.getInt(2));
				concern.setFriendId(resultSet.getInt(3));
				concern.setTime(resultSet.getString(4));
				listConcerns.add(concern);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (Concern[])listConcerns.toArray(new Concern[listConcerns.size()]);
	}
	
	
	
	
	
	
	public Concern(){};
	
	public Concern(int userId, int friendId)
	{
		this.userId = userId;
		this.friendId = friendId;
	}
	
	
	public void setId(int value)
	{
		this.id = value;
	}
	
	
	public int getId()
	{
		return this.id;
	}
	
	
	public void setUserId(int value)
	{
		this.userId = value;
	}
	
	
	public int getUserId()
	{
		return this.userId;
	}
	
	
	public void setFriendId(int value)
	{
		this.friendId = value;
	}
	
	
	public int getFriendId()
	{
		return this.friendId;
	}
	
	
	public void setTime(String value)
	{
		this.time = value;
	}
	
	
	public String getTime()
	{
		return this.time;
	}
}
