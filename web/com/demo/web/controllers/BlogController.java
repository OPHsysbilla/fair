package com.demo.web.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.mblog.io.DeleteHtml;
import com.mblog.io.WriteHtml;

import bean.Blog;
import bean.Comment;
import bean.Label;
import bean.User;
import operation.Delete;
import operation.Search;
import operation.Update;
 
@Controller
@SessionAttributes("sessionuser")
@RequestMapping(value = "/blog")
public class BlogController {  
	public static Integer PAGESIZE =  5;
	
	@AuthPassport
	@RequestMapping(value="/{userid}/{essayid}/edit",method = {RequestMethod.GET})
    public String EditEssayContent(Model model, 
    		@ModelAttribute("sessionuser")  User user ,
    		@PathVariable(value="userid") Integer userid,
			@PathVariable(value="essayid") Integer essayid,
		    HttpServletRequest request) throws IOException {
			   
			   Blog[] blog = Blog.getBlogById(essayid, Blog.MAINID);
			    if(blog.length<=0){
			    	model.addAttribute("exceptionMessage","无此文章");
			    	return "error";
			    }
			    Comment comment[] = Comment.getCommentByBlogId(essayid); 
				User commentuser[] = new User[comment.length]; 
				for(int i = 0;i<comment.length;i++){  
					commentuser[i] = User.getUserByID(comment[i].getUserId()); 
		 		}
				Label[] labels = Search.searchLabelByBlogId(essayid);
				model.addAttribute("essay",blog[0]);
				model.addAttribute("essayLabels",labels);
			   model.addAttribute("essayContent",blog[0].getContent()); 
			     
		      return "EssayUpdate";
		   }
	@RequestMapping(value="/{userid}/{essayid}/edit",method = {RequestMethod.POST})
    public String EditEssayContentPost(Model model, 
    		@PathVariable(value="userid") Integer userid,
			@PathVariable(value="essayid") Integer essayid,
    		@RequestParam(value="title")String title,
			@RequestParam(value="context")String context,
			@RequestParam(value="label",required=false)String label,
		    HttpServletRequest request) throws IOException {
				Blog[] blog = Blog.getBlogById(essayid, Blog.MAINID);
				System.out.println("edit Essay.");
			    if(blog.length<=0){
			    	model.addAttribute("exceptionMessage","无此文章");
			    	return "error";
			    }
			    String path = blog[0].getDirPath()  +"/blog.html";
			    System.out.println(path+"将被删除");
			   DeleteHtml deletehtml = new DeleteHtml();
			   deletehtml.delete(path);  		//删除相应文章，需要传值
			   CreateHtml createhtml = new CreateHtml();
			   String Filename = path;
			   createhtml.createFile(path);
			   request.setCharacterEncoding("UTF-8"); 
		       String BlogBody = context;
			   WriteHtml writehtml = new WriteHtml();
			   writehtml.write(Filename,context);
		       return "redirect:/blog/"+userid+"/"+essayid;
		      
//			   DeleteHtml deletehtml = new DeleteHtml();
//			   String path = blog[0].
//			   deletehtml.delete(path);  		//删除相应文章，需要传值
//			   String username = user.getUsername();
//			   CreateHtml createhtml = new CreateHtml();
//			   String Filename = path;
//			   createhtml.createFile(path);
			    
//			   request.setCharacterEncoding("UTF-8"); 
//			   System.out.print(request.getParameter("context"));
//		       String BlogBody = request.getParameter("context");
//			   WriteHtml writehtml = new WriteHtml();
//			   writehtml.write(Filename,BlogBody);
   }
	
	
	@AuthPassport
	@RequestMapping(value="/{userid}/{essayid}/delete")
    public String DeleteEssay(Model model, 
    		@ModelAttribute("sessionuser")  User user,
    		@PathVariable(value="userid") Integer userid,
			@PathVariable(value="essayid") Integer essayid ){	
		Delete.deleteBlogByBlogId(essayid);
		return "redirect:/blog/"+userid;
	}
	@RequestMapping(value="/{userid}/{essayid}", method = {RequestMethod.GET})
	public String GetEssayContent( Model model ,
			@PathVariable(value="userid") Integer userid,
			@PathVariable(value="essayid") Integer essayid){
	    Blog[] blog = Blog.getBlogById(essayid, Blog.MAINID);
	    if(blog.length<=0){
	    	model.addAttribute("exceptionMessage","无此文章");
	    	return "error";
	    }
//		htmlPath = "E:/mBlogDB/blogs/1495282283332/blog.html";
	    User essayauthor = User.getUserByID(blog[0].getUserID());
		Comment comment[] = Comment.getCommentByBlogId(essayid); 
		User commentuser[] = new User[comment.length]; 
		for(int i = 0;i<comment.length;i++){  
			commentuser[i] = User.getUserByID(comment[i].getUserId()); 
 		}
		
		Label[] labels = Search.searchLabelByBlogId(essayid);
		String essayLabels = "";
		for(Label l : labels){
			essayLabels+= l.getContent();			
		}
		model.addAttribute("essay",blog[0]);
		model.addAttribute("essayauthor",essayauthor);
		model.addAttribute("essayLabels",essayLabels);
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
			return "redirect:/account/login";
		}
		return "EssayEdit";
	}
	 
    @RequestMapping(value="/{userid}/newessaypost", method = {RequestMethod.POST})
    public String NewEssayContentPost(@PathVariable(value="userid") Integer userid,
    		Model model,
    		@RequestParam(value="title")String title,
			@RequestParam(value="context")String context,
			@RequestParam(value="label",required=false)String label){
       System.out.println(title+":"+context+":"+label);
       Blog blog = new Blog();
 	   blog.setTitle(title);
 	   blog.setAbstracts(Blog.getAbstracts(context));// 	   正则摘要
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
 		   System.out.print("Essay context:"+context);
// 	       String BlogBody = req.getParameter("context");
 		   String BlogBody = context;
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
    public String GetEssayCatalogById(
    		@RequestParam(value="curpage",required=false) Integer curpage,
    		@PathVariable(value="userid") Integer userid,
    		Model model){ 
    	System.out.println("userid="+userid+",curpage"+curpage);
    	if(curpage ==null){
			curpage = 1;
		}
    	int start = curpage - 1 ;
    	User foruser = User.getUserByID(userid);
    	Blog[] allblogs = Blog.getBlogById(foruser.getId(), Blog.USERID);
    	Blog[] blogs = Search.searchBlogByUsername(foruser.getUsername(), start, PAGESIZE);
    	if(blogs == null){
    		model.addAttribute("exceptionMessage","无此人");
    		return "error";
    	}
		for(Blog b:blogs){
			System.out.println(b.getTitle()+b.getId());
		}
    	model.addAttribute("alluserblog", blogs);
    	
    	Integer totalnum = allblogs.length; 
		Integer sumPage = totalnum/PAGESIZE;
		if(totalnum % PAGESIZE != 0){
			sumPage += 1;			
		}
		System.out.println("startindex:"+start);
		System.out.println("totalnum:"+totalnum); 
		System.out.println("sumPage:"+sumPage);  
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("sumpage",sumPage);
    	 
    	User author = User.getUserByID(userid);   
		model.addAttribute("alluserblogname", author);
    	 
//    	全都是同一个作者所以user[]中都是同一个User 
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