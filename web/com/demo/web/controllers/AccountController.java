package com.demo.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.demo.web.auth.AuthPassport;
import com.demo.web.models.AccountModel;

import bean.User;

@Controller  
@RequestMapping(value = "/account")
public class AccountController {  
	 
		
    @RequestMapping(value="/login", method = {RequestMethod.GET})
    public String login( Model model,User paramUser){
    	//登录页面 
        return "login";
	} 
    @RequestMapping(value="/login", method = {RequestMethod.POST})
    public String loginpost( 
    		HttpServletRequest request,
    		Model model, 
    		@RequestParam("username")String username,
    		@RequestParam("password")String password
    		){
    	//登录页面 
	    System.out.println(username+"-username");
	    System.out.println(password+"-password");
		User chkUser = User.getUserByName(username);
		if(chkUser == null || !chkUser.getPassword().equals(password))
		{
//			 验证失败  ,返回到登录界面
//        	getContextPath、getServletPath、getRequestURI 
//        	为/fair  
			model.addAttribute("error", "用户名或密码错误"); 
	        return "/login";
            //为true拦截器不进行后续拦截，现在来说这不重要
			
		}
		else {//如果验证成功
//			model.addAttribute("sessionuser",chkUser); 
			HttpSession session = request.getSession();
			session.setAttribute("sessionuser",chkUser); 
			model.addAttribute("sessionuser",chkUser); 
//			 HttpSession session = request.getSession();  
//             session.setAttribute("sessionuser", chkUser);   
////			request.getRequestDispatcher( "/"+"blog/"+chkUser.getId()).forward(request, response);  
//			response.sendRedirect( projectName+"/"+"blog/2" );
			return "redirect:/blog/"+chkUser.getId();
		}        		  
	}  
  
	@RequestMapping(value="/logout", method = {RequestMethod.GET})
	public String logout(HttpServletRequest request,@ModelAttribute("sessionuser") User user, SessionStatus sessionStatus){	
		//@ModelAttribute("sessionuser")相当于将session中名为"sessionuser"的对象注入user对象中
		//sessionStatus中的setComplete方法可以将session中的内容全部清空
		 
		sessionStatus.setComplete();
		request.getSession().removeAttribute("sessionuser");
		request.getSession().invalidate();
		return "redirect:/blog/2" ;
	}
    
    @RequestMapping(value="/register", method = {RequestMethod.GET})
    public String register(){ 
        //转到注册页面
        return "register";
    }
	 
    //POST方式带密码等参数 
    @RequestMapping(value="/registerpost", method = {RequestMethod.POST})
    public String registerpost(HttpServletRequest request,Model model,String registername,String registerpwd){ 
   
		//注册用户的代码
		User user = new User();
		user.setUsername(registername);
		user.setPassword(registerpwd);
		System.out.println(registername+","+registerpwd);
		if(User.addUser(user,false)) {
			//注册成功
	    	//转到登录 
	        return "redirect:login";
		}
		else {
			//"该用户名已经存在"
			//再次进入登陆界面 
			  model.addAttribute("error", "该用户名已经存在"); 
	          return "forward:/account/login";
		}
    }
    //个人信息
    @RequestMapping(value="/info/{userid}", method = {RequestMethod.GET})
    public String info(@PathVariable(value="userid") Integer userid){ 
        return "AccountInfo";
    }
    
    //修改个人信息
//    @RequestMapping(value="/selfinfo", method = {RequestMethod.GET})
//    public String info(@PathVariable(value="userid") Integer userid){ 
//        return "AccountInfo";
//    }
    
    
}














/*  使用注解的版本，没用       */

/*    
//带密码等参数
		@AuthPassport
	    @RequestMapping(value="/login", method = {RequestMethod.POST})
	    public String loginpost(HttpServletRequest request,Model model,String username){
	        //判断登录
			model.addAttribute("error", "用户名或密码不正确");
//	        	到这里肯定拦截器返回false验证失败
//	        	再次进入登陆界面 
	        return "forward:/account/login";
		}
		
//	        登录成功  后跳转，现在用拦截器方法，不用这个
//	        if(true){ 
//		        modelAndView.setViewName("forward:/blog/1");   
//		    } 
 
*/         





//public String loginpost(Model model,@RequestParam("username")String username,@RequestParam("password")String password){
/*  
@RequestMapping(value="/login", method = {RequestMethod.POST})
public String loginpost(Model model, @ModelAttribute("sessionuser")  User paramUser){
	//登录操作的代码（用户名和密码保存在username,password里
	User user = User.getUserByName(paramUser.getUsername());
	System.out.println(paramUser.getUsername()+","+paramUser.getPassword());
	if(user == null || !user.getPassword().equals(paramUser.getPassword()))
	{ 
		//form后面的/必须有
		// 验证失败再次进入登陆界面   
		model.addAttribute("error","用户名或密码不正确");
      return "/login";
	}
	else { 
		//登录成功，user加入session    .put("user", user); 
		//model.addAttribute("sessionuser", user); 
		return "redirect:/blog/"+user.getId();
	} 
} 
*/
