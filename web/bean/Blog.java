package bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;
import database.MyEnviroment;

public class Blog{
	private int id;
	private int userId;
	private String dirPath;
	private String title;
	private String abstracts;
	private String time;
	private String[] labels;
	
	
	public final static int MAINID = 0;
	public final static int USERID = 1;

	
	
	public Blog()
	{
		
	}
	
	public String getContent()
	{
		String content = "";
		try {
			FileInputStream fis = new FileInputStream(this.getDirPath() + "/blog.html");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			String tmp;
			while( (tmp = reader.readLine()) != null)
			{
				content += tmp;
			}
			reader.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public File[] getAllFile()
	{
		File dir = new File(this.getDirPath() + "/file");
		return dir.listFiles();
	}
	
	public File[] getAllImage()
	{
		File dir = new File(this.getDirPath() + "/image");
		return dir.listFiles();
	}
	
	
	public static boolean addBlog(Blog blog)
	{
		String sqlInsert = "insert into blog_table values(" 
				+ "NULL" + ",'" 
				+ blog.getUserID() + "','" 
				+ blog.dirPath + "','"
				+ blog.getTitle() + "','"
				+ blog.getAbstacts() + "',"
				+ "NOW()" + ")";
		return(DBQueryHelper.executeUpdate(sqlInsert));
	}
	
	
	public static boolean deleteBlogById(int ID)
	{
		String sqlDelete = "delete from blog_table where id = '" + ID + "'";
		return DBQueryHelper.execute(sqlDelete);
	}
	
	
	public static Blog[] getAllBlogs()
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from blog_table");
		ArrayList<Blog> listBlogs = new ArrayList<Blog>();
  		try {
			while(resultSet.next())
			{
				Blog blog = new Blog();
				blog.setId(resultSet.getInt(1));
				blog.setUserID(resultSet.getInt(2));
				blog.setDirPath(resultSet.getString(3));
				blog.setTitle(resultSet.getString(4));
				blog.setAbstracts(resultSet.getString(5));
				blog.setTime(resultSet.getString(6));
				listBlogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (Blog[])listBlogs.toArray(new Blog[listBlogs.size()]);
	}
	
	
	public static Blog[] getBlogById(int ID, int IDTYPE)
	{
		ResultSet resultSet = null;
		if(IDTYPE == MAINID)
		{
			resultSet = DBQueryHelper.executeQuery("select * from blog_table where id = '" + ID + "'");
		}
		else if(IDTYPE == USERID)
		{
			resultSet = DBQueryHelper.executeQuery("select * from blog_table where userId = '" + ID + "'");
		}
		ArrayList<Blog> listBlogs = new ArrayList<Blog>();
  		try {
			while(resultSet!= null && resultSet.next())
			{
				Blog blog = new Blog();
				blog.setId(resultSet.getInt(1));
				blog.setUserID(resultSet.getInt(2));
				blog.setDirPath(resultSet.getString(3));
				blog.setTitle(resultSet.getString(4));
				blog.setAbstracts(resultSet.getString(5));
				blog.setTime(resultSet.getString(6));
				listBlogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (Blog[])listBlogs.toArray(new Blog[listBlogs.size()]);
	}
	
	
	public static Blog getBlogByTimeMillis(long time)
	{
		ResultSet resultSet = DBQueryHelper.executeQuery("select * from blog_table where path = '" + time + "'");
		try {
			if(resultSet.next())
			{
				Blog blog = new Blog();
				blog.setId(resultSet.getInt(1));
				blog.setUserID(resultSet.getInt(2));
				blog.setDirPath(resultSet.getString(3));
				blog.setTitle(resultSet.getString(4));
				blog.setAbstracts(resultSet.getString(5));
				blog.setTime(resultSet.getString(6));
				return blog;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public void setId(int value)
	{
		this.id = value;
	}
	
	
	public int getId()
	{
		return this.id;
	}
	
	
	public void setUserID(int value)
	{
		this.userId = value;
	}
	
	
	public int getUserID()
	{
		return this.userId;
	}
	
	
	public void setDirPath(String value)
	{
		this.dirPath = value;
	}
	
	public static String getRootPath()
	{
		return MyEnviroment.ROOTPATH;
		
	}
	
	public String getDirPath()
	{
		return MyEnviroment.BLOGSPATH + this.dirPath;
	}
	
	
	public void setTitle(String value)
	{
		this.title = value;
	}
	
	
	public String getTitle()
	{
		return this.title;
	}
	
	
	public void setAbstracts(String value)
	{
		this.abstracts = value;
	}
	
	
	public String getAbstacts()
	{
		return this.abstracts;
	}
	
	
	public void setTime(String value)
	{
		this.time = value;
	}
	
	
	public String getTime()
	{
		return this.time;
	}
	

	public int getUserId() {
		return userId;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
