package operation;

import bean.Blog;
import bean.Comment;
import bean.CommentOnComment;
import bean.ContactBlogLabel;

public class Delete {
	public static boolean deleteBlogByBlogId(int id) //通过博客的id来删除博客，以及和该博客有关的所有评论
	{
		Blog.deleteBlogById(id);
		ContactBlogLabel.deleteContactBlogLabelById(id, ContactBlogLabel.BLOGID);
		Comment.deleteCommentById(id, Comment.BLOGID);
		CommentOnComment.deleteCommentById(id, CommentOnComment.BLOGID);
		return true;
	}
	
	
	public static boolean deleteCommentByCommentId(int id)//通过评论的id来删除评论，以及对该评论的评论
	{
		Comment.deleteCommentById(id, Comment.MAINID);
		CommentOnComment.deleteCommentById(id, CommentOnComment.COMMENTID);
		return true;
	}
}
