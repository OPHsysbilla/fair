package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBQueryHelper;

public class Label {
	private int id;
	private String content;
	
	public Label(String content)
	{
		this.content = content;
	}
	
	
	public Label(){};
	
	public void setId(int value)
	{
		this.id = value;
	}
	
	
	public int getId()
	{
		return this.id;
	}
	
	
	public void setContent(String value)
	{
		this.content = value;
	}
	
	
	public String getContent()
	{
		return this.content;
	}
	
	
	public static boolean addLabel(Label label)
	{
		if(getLabelByContent(label.getContent()) == null)
		{
			String sqlInsert = "insert into label_table values(" 
					+ "NULL" + ",'" 
					+ label.getContent() + "')";
			return DBQueryHelper.executeUpdate(sqlInsert);
		}
		return false;
	}
	
	public static int getIdByContent(String content)
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select id from label_table where content = '" + content + "'");
  		try {
  			if(resultSet.next()) //有相关记录
			{
  				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return -1; //无相关记录，返回-1
	}
	
	public static Label getLabelByContent(String content)
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from label_table where content = '" + content + "'");
  		try {
  			if(resultSet.next()) //有相关记录
			{
  				Label label = new Label();
  				label.setId(resultSet.getInt(1));
  				label.setContent(resultSet.getString(2));
  				return label;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return null; //无相关记录，返回null
	}
	
	
}
