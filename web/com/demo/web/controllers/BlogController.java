package com.demo.web.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.demo.web.auth.AuthPassport;
import com.demo.web.models.AccountModel;

import bean.Blog;
import bean.Comment;
import bean.User;
 
@Controller
@SessionAttributes("sessionuser")
@RequestMapping(value = "/blog")
public class BlogController { 
	@ModelAttribute("sessionuser")
	public User initUser(){ 
	    return new User();
	}
	
	
	@RequestMapping(value="/{userid}/{essayid}/edit", method = {RequestMethod.GET})
    public String EditEssayContent(Model model, @ModelAttribute("sessionuser")  User user){	
	
		return "EssayEdit";
	}
	@RequestMapping(value="/{userid}/{essayid}", method = {RequestMethod.GET})
	public String GetEssayContent( Model model ,@PathVariable(value="userid") Integer userid,@PathVariable(value="essayid") Integer essayid){
	    Blog[] blog = Blog.getBlogById(essayid, Blog.MAINID);
		 
//		htmlPath = "E:/mBlogDB/blogs/1495282283332/blog.html";

		Comment comment[] = Comment.getCommentByBlogId(essayid); 
		User commentuser[] = new User[comment.length];
		for(int i = 0;i<comment.length;i++){
			
			commentuser[i] = User.getUserByID(comment[i].getUserId()); 
		}
		model.addAttribute("essay",blog[0]);
		model.addAttribute("essayContent",blog[0].getContent()); 
		System.out.println(blog[0].getContent());
		model.addAttribute("essayCommentUser",commentuser);
		model.addAttribute("essayComment", comment); 
		
		
		return "EssayContent";
	}
	@RequestMapping(value="/{userid}/newessay", method = {RequestMethod.GET})
	public String NewEssayContent(Model model, @PathVariable(value="userid") Integer userid,@ModelAttribute("sessionuser")  User user){
		System.out.println("username:"+user.getUsername()+", new Essay.");
		//证明没有登录，需要登录先
		if(user.getUsername() == null){			
			model.addAttribute("error","请先登录");
			//return "forward:/account/login";
			return "redirect:/account/login";
		}
		
		
		
		
		return "EssayEdit";
	}
    @RequestMapping(value="/{userid}", method = {RequestMethod.GET})
    public String GetEssayCatalogById(@PathVariable(value="userid") Integer userid,Model model){
    	Blog blog[] = Blog.getBlogById(userid, Blog.USERID);
    	model.addAttribute("alluserblog", blog);
    	if(blog!=null){
    		User author = User.getUserByID(blog[0].getUserID());   
    		model.addAttribute("alluserblogname", author);
    	}
    	
//    	全都是同一个作者所以user[]中都是同一个User
//    	User user[] = new User[blog.length];
//    	for(int i= 0 ;i<blog.length;i++){
//    		user[i] = User.getUserByID(blog[i].getUserID());    		
//    	}
//    	model.addAttribute("alluserblogname", user);
    	System.out.println("userid="+userid);
//		session中已经有user，故不需要modelAndView.addObject("userid", userid);    
        return "EssayCatalog";  
    }
    @RequestMapping(value="/{userid}/{essayid}",params="method=addComment", method = {RequestMethod.POST})
	public String AddEssayComment( Model model, 
			@ModelAttribute("sessionuser")  User user, 
			@RequestParam("comment")String commentcontent,
			@PathVariable(value="userid") Integer userid,
			@PathVariable(value="essayid") Integer essayid){
    	
		System.out.println("username:"+user.getUsername()+", comment:"+commentcontent);
		//证明没有登录，需要登录先
		if(user.getUsername() == null){			
			model.addAttribute("error","请先登录");
			return "forward:/account/login";
		}
		Comment comment = new Comment();
		comment.setUserId(userid);
		comment.setBlogId(essayid);
		comment.setContent(commentcontent);
		Comment.addComment(comment); 
		
		return "EssayEdit";
	}
}