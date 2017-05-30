package com.demo.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import bean.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            String projectName = request.getContextPath();
            //没有声明需要权限,或者声明不验证权限
            if(authPassport == null || authPassport.validate() == false)
                return true;
            else{                
                //实现权限验证逻辑 
        		User user = (User)request.getSession().getAttribute("sessionuser");
        		if(user == null){
        			System.out.println("user is null");
                    response.sendRedirect( projectName+"/"+"account/login");
                    return false; 
                    //为true拦截器不进行后续拦截，现在来说这不重要
        		}
        		else if(user.getUsername()== null){
        			System.out.println("user.getUsername() is null");
                    response.sendRedirect( projectName+"/"+"account/login");
                    return false; 
                    //为true拦截器不进行后续拦截，现在来说这不重要
        		} 
        		else {// 登录状态
//        			HttpSession session = request.getSession();  
//		            session.setAttribute("sessionuser", chkUser);   
////        			request.getRequestDispatcher( "/"+"blog/"+chkUser.getId()).forward(request, response);  
//        			response.sendRedirect( projectName+"/"+"blog/2" );
        			return true;
        		}        		 
                    
            }
        }
        else
            return true;   
     }
}



/*
//实现权限验证逻辑
String username = request.getParameter("username");
String password = request.getParameter("password");
System.out.println(username+"-username,"+password+"-password"); 
User chkUser = User.getUserByName(username);
if(chkUser == null || !chkUser.getPassword().equals(password))
{
//	如果验证失败
//	返回到登录界面
//	getContextPath、getServletPath、getRequestURI 
//	为/fair 
	 
    response.sendRedirect( projectName+"/"+"account/login");

    return false; 
    //为true拦截器不进行后续拦截，现在来说这不重要
	
}
else {//如果验证成功
	 HttpSession session = request.getSession();  
     session.setAttribute("sessionuser", chkUser);   
//	request.getRequestDispatcher( "/"+"blog/"+chkUser.getId()).forward(request, response);  
	response.sendRedirect( projectName+"/"+"blog/2" );
	return true;
}        		
 */