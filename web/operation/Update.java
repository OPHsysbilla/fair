package operation;
import java.io.File;

import bean.Blog;
import bean.ContactBlogLabel;
import bean.Label;
import database.DBQueryHelper;
import database.MyEnviroment;

public class Update {
	
	public static String addBlog(Blog blog, Label[] labels)
	{
		long time = System.currentTimeMillis();
		String dirPath = "" + time;
		File file = new File(MyEnviroment.BLOGSPATH + dirPath);
		file.mkdir();
		File accessory = new File(MyEnviroment.BLOGSPATH + dirPath + "/file");
		accessory.mkdir();
		File images = new File(MyEnviroment.BLOGSPATH + dirPath + "/image");
		images.mkdir();
		blog.setDirPath(dirPath);
		Blog.addBlog(blog);
		if(labels != null)
		{
			Blog lastInsertBlog = Blog.getBlogByTimeMillis(time);
			System.out.println("label不为空");
			for(Label tmpLabel:labels)
			{
				Label.addLabel(tmpLabel);
			}
			ContactBlogLabel[] ctbls = new ContactBlogLabel[labels.length];
			for(int i = 0; i < labels.length; i++)
			{
				ctbls[i] = new ContactBlogLabel();
				ctbls[i].setBlogId(lastInsertBlog.getId());
				ctbls[i].setLabelId(Label.getIdByContent(labels[i].getContent()));
			}
			for(int i = 0; i < labels.length; i++)
			{
				ContactBlogLabel.addContactBlogLabel(ctbls[i]);
			}
		}
		return MyEnviroment.BLOGSPATH + dirPath;
	}
	
	
	public static boolean changePasswordByUserId(int userId, String newPassword)
	{
		String sqlUpdate = "update user_table set password='" + newPassword + 
				"' where id = '" + userId + "'";
		return DBQueryHelper.executeUpdate(sqlUpdate);
	}
}
