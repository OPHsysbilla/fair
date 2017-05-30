package com.demo.web.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.demo.web.auth.AuthPassport;
import com.demo.web.models.AccountModel;
import com.mblog.io.CreateHtml;
import com.mblog.io.WriteHtml;

import bean.Blog;
import bean.Comment;
import bean.Label;
import bean.User;
import operation.Search;
import operation.Update;
 
@Controller
@SessionAttributes("sessionuser")
@RequestMapping(value = "/blog")
public class BlogController {  
	@AuthPassport
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
		Label[] labels = Search.searchLabelByBlogId(essayid);
		model.addAttribute("essay",blog[0]);
		model.addAttribute("essayLabels",labels);
		model.addAttribute("essayContent",blog[0].getContent()); 
		System.out.println(blog[0].getContent());
		model.addAttribute("essayCommentUser",commentuser);
		model.addAttribute("essayComment", comment); 
		
		return "EssayContent";
	}
//	 
	@AuthPassport
	@RequestMapping(value="/{userid}/newessay", method = {RequestMethod.GET})
	public String NewEssayContent(HttpServletRequest request,
			Model model,  
			@ModelAttribute("sessionuser")  User user,
			@PathVariable(value="userid") Integer userid
			){
//		User user = (User)request.getSession().getAttribute("sessionuser");
		System.out.println("username:"+user.getUsername()+", new Essay.");
		//证明没有登录，需要登录先
		if(user.getUsername() == null){			
			model.addAttribute("error","请先登录");
			//return "forward:/account/login";
			return "redirect:/account/login";
		}
		return "EssayEdit";
	}
	 
    @RequestMapping(value="/{userid}/newessaypost", method = {RequestMethod.POST})
    public String NewEssayContentPost(@PathVariable(value="userid") Integer userid,
    		Model model,
    		@RequestParam(value="title")String title,
			@RequestParam(value="context")String content,
			@RequestParam(value="label",required=false)String label){
       System.out.println(title+":"+content+":"+label);
       Blog blog = new Blog();
 	   blog.setTitle(title);
 	   blog.setAbstracts(Blog.getAbstracts(content));// 	   正则摘要
 	   blog.setUserID(userid);
 	   String[] labelStr = label.split(",");
 	   Label[] labels = new Label[labelStr.length];
 	   for(int i =0;i<labelStr.length;i++){
 		   String aString = labelStr[i];
 		   labels[i] = new Label();
 		   labels[i].setContent(aString); 
 	   } 
 	   String dirPath = Update.addBlog(blog, labels);
 	   
 	   CreateHtml createhtml = new CreateHtml();
 	   createhtml.createDir(dirPath);
 	   String fileName = dirPath + "/blog.html";
 	   createhtml.createFile(fileName);
 	   HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
  
 	   try {
 		   req.setCharacterEncoding("UTF-8");
 		   System.out.print("Essay context:"+req.getParameter("context"));
 	       String BlogBody = req.getParameter("context");
 		   System.out.print(BlogBody);
 		   WriteHtml writehtml = new WriteHtml();
 		   writehtml.write(fileName,BlogBody);
 		} catch (UnsupportedEncodingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}  
 		return "redirect:/blog/"+userid;
    }
	
    @RequestMapping(value="/{userid}", method = {RequestMethod.GET})
    public String GetEssayCatalogById(@PathVariable(value="userid") Integer userid,Model model){
    	Blog blog[] = Blog.getBlogById(userid, Blog.USERID);
//    	Blog blog[] = Search.
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
    @AuthPassport
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
		
		return "redirect:/blog/"+userid+"/"+essayid;
	}
}