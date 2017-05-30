package operation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Blog;
import bean.ContactBlogLabel;
import bean.Label;
import bean.User;
import database.DBQueryHelper;

public class Search {
	public static Blog[] searchBlogByTitle(String title) //按标题来查询博客
	{
		Blog[] blogs = Blog.getAllBlogs();
		ArrayList<Blog> searchedBlogs = new ArrayList<Blog>();
		for(Blog tmpBlog : blogs)
		{
			Pattern pattern = Pattern.compile(title);
			Matcher matcher = pattern.matcher(tmpBlog.getTitle());
			if(matcher.find())
			{
				searchedBlogs.add(tmpBlog);
			}
		}
		return (Blog[])searchedBlogs.toArray(new Blog[searchedBlogs.size()]);
	}
	
	
	public static Blog[] searchBlogByTitle(String title, int pageNum, int pageSize) //按标题来查询博客
	{
		Blog[] blogs = Blog.getAllBlogs();
		ArrayList<Blog> searchedBlogs = new ArrayList<Blog>();
		for(Blog tmpBlog : blogs)
		{
			Pattern pattern = Pattern.compile(title);
			Matcher matcher = pattern.matcher(tmpBlog.getTitle());
			if(matcher.find())
			{
				searchedBlogs.add(tmpBlog);
			}
		}
		ArrayList<Blog> listCertainBlogs = new ArrayList<Blog>();
		if(pageNum * pageSize >= searchedBlogs.size())
		{
			return (Blog[])listCertainBlogs.toArray(new Blog[listCertainBlogs.size()]);
		}	
		for(int i = 0; i < pageSize; i++)
		{
			int index = i + pageNum * pageSize;
			if(index >= searchedBlogs.size())
				break;
			listCertainBlogs.add(searchedBlogs.get(index));
		}
		return (Blog[])listCertainBlogs.toArray(new Blog[listCertainBlogs.size()]);
	}
	
	public static Blog[] searchBlogByLabel(String label)  //按标签来查询博客
	{
		ArrayList<Blog> searchedBlogs = new ArrayList<Blog>();
		int labelId = Label.getIdByContent(label);
		ContactBlogLabel[] cbls = 
				ContactBlogLabel.getContactBlogLabelsByID(labelId, ContactBlogLabel.LABELID);
		for(ContactBlogLabel tmpContacts : cbls)
		{
			Blog[] tmpBlogs = Blog.getBlogById(tmpContacts.getBlogId(), Blog.MAINID);
			for(int i = 0; i < tmpBlogs.length; i++)
			{
				searchedBlogs.add(tmpBlogs[i]);
			}
		}
		return (Blog[])searchedBlogs.toArray(new Blog[searchedBlogs.size()]);
	}
	
	
	public static Blog[] searchBlogByLabel(String label, int pageNum, int pageSize)  //按标签来查询博客
	{
		ArrayList<Blog> searchedBlogs = new ArrayList<Blog>();
		int labelId = Label.getIdByContent(label);
		ContactBlogLabel[] cbls = 
				ContactBlogLabel.getContactBlogLabelsByID(labelId, ContactBlogLabel.LABELID);
		for(ContactBlogLabel tmpContacts : cbls)
		{
			Blog[] tmpBlogs = Blog.getBlogById(tmpContacts.getBlogId(), Blog.MAINID);
			for(int i = 0; i < tmpBlogs.length; i++)
			{
				searchedBlogs.add(tmpBlogs[i]);
			}
		}
		ArrayList<Blog> listCertainBlogs = new ArrayList<Blog>();
		if(pageNum * pageSize >= searchedBlogs.size())
		{
			return (Blog[])listCertainBlogs.toArray(new Blog[listCertainBlogs.size()]);
		}	
		for(int i = 0; i < pageSize; i++)
		{
			int index = i + pageNum * pageSize;
			if(index >= searchedBlogs.size())
				break;
			listCertainBlogs.add(searchedBlogs.get(index));
		}
		return (Blog[])listCertainBlogs.toArray(new Blog[listCertainBlogs.size()]);
	}
	
	public static Blog[] searchBlogByUsername(String username) //按用户名来查询博客
	{
		ArrayList<Blog> searchedBlogs = new ArrayList<Blog>();
		User user = User.getUserByName(username);
		for(Blog tmpBlog : Blog.getBlogById(user.getId(), Blog.USERID))
		{
			searchedBlogs.add(tmpBlog);
		}
		return (Blog[])searchedBlogs.toArray(new Blog[searchedBlogs.size()]);
	}
	
	
	public static Blog[] searchBlogByUsername(String username, int pageNum, int pageSize) //按用户名来查询博客
	{
		ArrayList<Blog> searchedBlogs = new ArrayList<Blog>();
		User user = User.getUserByName(username);
		for(Blog tmpBlog : Blog.getBlogById(user.getId(), Blog.USERID))
		{
			searchedBlogs.add(tmpBlog);
		}
		ArrayList<Blog> listCertainBlogs = new ArrayList<Blog>();
		if(pageNum * pageSize >= searchedBlogs.size())
		{
			return (Blog[])listCertainBlogs.toArray(new Blog[listCertainBlogs.size()]);
		}	
		for(int i = 0; i < pageSize; i++)
		{
			int index = i + pageNum * pageSize;
			if(index >= searchedBlogs.size())
				break;
			listCertainBlogs.add(searchedBlogs.get(index));
		}
		return (Blog[])listCertainBlogs.toArray(new Blog[listCertainBlogs.size()]);
	}
	
	
	
	//传入博客id，获得和这篇博客绑定的标签
	public static Label[] searchLabelByBlogId(int blogId)
	{
		ArrayList<Label> searchedLabels = new ArrayList<Label>();
		ContactBlogLabel[] contacts = ContactBlogLabel.getContactBlogLabelsByID(blogId, ContactBlogLabel.BLOGID);
		for(int i = 0; i < contacts.length; i++)
		{
			searchedLabels.add(Label.getLabelById(contacts[i].getLabelId()));
		}
		return (Label[])searchedLabels.toArray(new Label[searchedLabels.size()]);
	}
	
	
	
	public static Blog[] searchLatestedNBlogs(int num)
	{
		ArrayList<Blog> listBlogs = new ArrayList<Blog>();
		ResultSet resultSet = DBQueryHelper.executeQuery(
				"SELECT * FROM blog_table ORDER BY blog_table.id DESC LIMIT " + num);
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
}
