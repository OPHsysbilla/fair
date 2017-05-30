package com.demo.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.web.auth.AuthPassport;

import bean.Blog;
import bean.User;
import operation.Search;
 
@Controller 
public class HelloWorldController { 
	public static Integer PAGESIZE =  5;
	@RequestMapping(value={"/miao"})
	public String miao(HttpServletRequest request, Model model){ 
	    return "miao";
	}
	@RequestMapping(value={"/index"})
	public String index(HttpServletRequest request, Model model ){ 
		System.out.println("in index"); 
		Blog[] allblogs = Search.searchLatestedNBlogs(PAGESIZE);//(foruser.getId(), Blog.USERID);
//		Blog[] blogs = Search.searchBlogByUsername(foruser.getUsername(), start, PAGESIZE);
		User[] allblogsuser = new User[allblogs.length]; 
		for(int i = 0;i<allblogsuser.length;i++){
			allblogsuser[i] = User.getUserByID(allblogs[i].getUserId());
			System.out.println(allblogs[i].getTitle()+","+allblogs[i].getId()+"--"+allblogsuser[i].getUsername());
		}
		model.addAttribute("allblogs", allblogs); 
		model.addAttribute("allblogsuser", allblogsuser); 
	    return "index";
	}
 
	
	@AuthPassport
    @RequestMapping(value="/detail/{id}", method = {RequestMethod.GET})
    public ModelAndView getDetail(@PathVariable(value="id") Integer id){
        
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.addObject("id", id);  
        modelAndView.setViewName("detail");  
        return modelAndView;
    }
}