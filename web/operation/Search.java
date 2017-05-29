package operation;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Blog;
import bean.ContactBlogLabel;
import bean.Label;
import bean.User;

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
	
	
	
}
