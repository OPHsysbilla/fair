package com.mblog.blogdata;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mblog.io.DeleteHtml;

import bean.Blog;
import bean.Comment;
import bean.CommentOnComment;
import bean.ContactBlogLabel;

@Controller
public class DeleteData {
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session){
		int id = (int)session.getAttribute("blogid");
		Blog blog = new Blog();
		blog.deleteBlogById(id);
		Comment comment = new Comment();
		comment.deleteCommentById(id, 0);
		CommentOnComment commentoncomment = new CommentOnComment();
		commentoncomment.deleteCommentById(id, 2);
		ContactBlogLabel contactbloglabel = new ContactBlogLabel();
		contactbloglabel.deleteContactBlogLabelById(id, 0);
		String path = (String)session.getAttribute("path");
		DeleteHtml deletehtml = new DeleteHtml();
		deletehtml.delete(path);
		return "result";		//返回地址，应该是要改的
	}
}
