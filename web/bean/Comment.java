package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;

public class Comment {
	private int id;
	private int blogId;
	private int userId;
	private String content;
	private String time;
	
	 
	
	public final static int MAINID = 0;
	public final static int BLOGID = 0;
	public final static int USERID = 0;
	
	
	public static boolean addComment(Comment comment)
	{
		String sqlInsert = "insert into comment_table values(" 
				+ "NULL" + ",'" 
				+ comment.getBlogId() + "','" 
				+ comment.getUserId() + "','"
				+ comment.getContent() + "',"
				+ "NOW()" + ")";
		return DBQueryHelper.executeUpdate(sqlInsert);
	}
	
	
	public static boolean deleteCommentById(int ID, int IDTYPE)
	{
		String sqlDelete = "";
		if(IDTYPE == MAINID)
			sqlDelete = "delete from comment_table where id = '" + ID + "'";
		else if(IDTYPE == BLOGID)
			sqlDelete = "delete from comment_table where blogId = '" + ID + "'";
		else if(IDTYPE == USERID)
			sqlDelete = "delete from comment_table where userId = '" + ID + "'";
		return DBQueryHelper.execute(sqlDelete);
	}
	
	
	public static Comment[] getCommentByBlogId(int blogID)
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from comment_table where blogId = '" + blogID + "'");
		ArrayList<Comment> listComments = new ArrayList<Comment>();
  		try {
			while(resultSet.next())
			{
				Comment comment = new Comment();
				comment.setId(resultSet.getInt(1));
				comment.setBlogId(resultSet.getInt(2));
				comment.setUserId(resultSet.getInt(3));
				comment.setContent(resultSet.getString(4));
				comment.setTime(resultSet.getString(5));
				listComments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (Comment[])listComments.toArray(new Comment[listComments.size()]);
	}
	
	
	public Comment(){}
	
	
	public void setId(int value)
	{
		this.id = value;
	}
	
	
	public int getId()
	{
		return this.id;
	}
	
	
	public void setBlogId(int value)
	{
		this.blogId = value;
	}
	
	
	public int getBlogId()
	{
		return this.blogId;
	}
	
	
	public void setUserId(int value)
	{
		this.userId = value;
	}
	
	
	public int getUserId()
	{
		return this.userId;
	}
	
	
	public void setContent(String value)
	{
		this.content = value;
	}
	
	
	public String getContent()
	{
		return this.content;
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
