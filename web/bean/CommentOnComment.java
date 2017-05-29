package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;

public class CommentOnComment{
	private int id;
	private int blogId;
	private int commentId;
	private int userId;
	private String content;
	private String time;
	public static int COMMENTID = 0;
	public static int USERID = 1;
	public static int BLOGID = 2;
	public static int MYID = 3;
	
	public CommentOnComment(){}
	
	
	public static boolean deleteCommentById(int ID, int IDTYPE)
	{
		String sqlDelete = "";
		if(IDTYPE == COMMENTID)
			sqlDelete = "delete from comment_on_comment_table where commentId = '" + ID + "'";
		else if(IDTYPE == USERID)
			sqlDelete = "delete from comment_on_comment_table where userId = '" + ID + "'";
		else if(IDTYPE == BLOGID)
			sqlDelete = "delete from comment_on_comment_table where blogId = '" + ID + "'";
		else if(IDTYPE == MYID)
			sqlDelete = "delete from comment_on_comment_table where id = '" + ID + "'";
		return DBQueryHelper.execute(sqlDelete);
	}
	
	
	public static boolean addCommentOnComment(CommentOnComment coc)
	{
		String sqlInsert = "insert into comment_on_comment_table values(" 
				+ "NULL" + ",'" 
				+ coc.getBlogId() + "','" 
				+ coc.getCommentId() + "','" 
				+ coc.getUserId() + "','"
				+ coc.getContent() + "',"
				+ "NOW()" + ")";
		return DBQueryHelper.executeUpdate(sqlInsert);
	}
	
	
	public static CommentOnComment[] getCommentById(int ID, int IDTYPE)
	{
		ResultSet resultSet = null;
		if(IDTYPE == COMMENTID)
			resultSet = DBQueryHelper.executeQuery("select * from comment_on_comment_table where commentId = '" + ID + "'");
		else if(IDTYPE == USERID)
			resultSet = DBQueryHelper.executeQuery("select * from comment_on_comment_table where userId = '" + ID + "'");
		else if(IDTYPE == BLOGID)
			resultSet = DBQueryHelper.executeQuery("select * from comment_on_comment_table where blogId = '" + ID + "'");
		else if(IDTYPE == MYID)
			resultSet = DBQueryHelper.executeQuery("select * from comment_on_comment_table where id = '" + ID + "'");
		ArrayList<CommentOnComment> listComments = new ArrayList<CommentOnComment>();
  		try {
			while(resultSet.next())
			{
				CommentOnComment commentOnComment = new CommentOnComment();
				commentOnComment.setId(resultSet.getInt(1));
				commentOnComment.setBlogId(resultSet.getInt(2));
				commentOnComment.setCommentId(resultSet.getInt(3));
				commentOnComment.setUserId(resultSet.getInt(4));
				commentOnComment.setContent(resultSet.getString(5));
				commentOnComment.setTime(resultSet.getString(6));
				listComments.add(commentOnComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (CommentOnComment[])listComments.toArray(new CommentOnComment[listComments.size()]);
	}
	
	
	
	
	
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
	
	
	public void setCommentId(int value)
	{
		this.commentId = value;
	}
	
	
	public int getCommentId()
	{
		return this.commentId;
	}
}
