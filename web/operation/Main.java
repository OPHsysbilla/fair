package operation;

import bean.User;

public class Main {
	public static void main(String args[])
	{
		/*Blog blog[] = Search.searchBlogByTitle("博客");	//找到所有标题中带"博客"二字的博客
		for(Blog b : blog)
		{
			System.out.println(b.getTitle()); //输出博客的标题
			for(File f:b.getAllFile())//输出该博客的所有附件的文件名和绝对路径
			{
				System.out.println(f.getName() + "___" + f.getAbsolutePath());
			}
		}*/
		//Update.changePasswordByUserId(3, "miaomiaomiao");
		User user = new User();
		user.setUsername("Jerry");
		user.setPassword("123456");
		User.addUser(user,false);
		System.out.println(user.getFilesDir());
	}
}
