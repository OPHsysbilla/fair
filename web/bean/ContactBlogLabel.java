package bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBQueryHelper;

public class ContactBlogLabel {
	private int blogId;
	private int labelId;
	public final static int BLOGID = 0;
	public final static int LABELID = 1;
	
	
	public static ContactBlogLabel[] getContactBlogLabelsByID(int ID, int IDTYPE)
	{
		ResultSet resultSet = null;
		if(IDTYPE == BLOGID)
			resultSet = DBQueryHelper.executeQuery("select * from contact_blog_label where blogId = '" + ID + "'");
		else if(IDTYPE == LABELID)
			resultSet = DBQueryHelper.executeQuery("select * from contact_blog_label where labelId = '" + ID + "'");
		ArrayList<ContactBlogLabel> listContacts = new ArrayList<ContactBlogLabel>();
  		try {
			while(resultSet.next())
			{
				ContactBlogLabel contactBlogLabel = new ContactBlogLabel();
				contactBlogLabel.setBlogId(resultSet.getInt(1));
				contactBlogLabel.setLabelId(resultSet.getInt(2));
				listContacts.add(contactBlogLabel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
  		return (ContactBlogLabel[])listContacts.toArray(new ContactBlogLabel[listContacts.size()]);
	}
	
	
	public static boolean addContactBlogLabel(ContactBlogLabel cbl)
	{
		String sqlInsert = "insert into contact_blog_label values('" 
				+ cbl.getBlogId() + "','" 
				+ cbl.getLabelId() + "')";
		return DBQueryHelper.executeUpdate(sqlInsert);
	}
	
	
	public static boolean deleteContactBlogLabelById(int ID, int IDTYPE)
	{
		String sqlDelete = "";
		if(IDTYPE == BLOGID)
			sqlDelete = "delete from contact_blog_label where blogId = '" + ID + "'";
		else if(IDTYPE == LABELID)
			sqlDelete = "delete from contact_blog_label where labelId = '" + ID + "'";
		return DBQueryHelper.execute(sqlDelete);
	}
	
	
	public ContactBlogLabel(){}
	
	
	public ContactBlogLabel(int blogId, int labelId)
	{
		this.blogId = blogId;
		this.labelId = labelId;
	}
	
	
	public void setBlogId(int value)
	{
		this.blogId = value;
	}
	
	
	public int getBlogId()
	{
		return this.blogId;
	}
	
	
	public void setLabelId(int value)
	{
		this.labelId = value;
	}
	
	
	public int getLabelId()
	{
		return this.labelId;
	}
}
