package com.mblog.blogdata;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mblog.io.CreateHtml;
import com.mblog.io.DeleteHtml;
import com.mblog.io.WriteHtml;

@Controller
public class UpdateData {
	 @RequestMapping(value = "/update", method = RequestMethod.GET)
	   public ModelAndView provide_data(HttpSession	session) {
		 session.setAttribute("name", "miao");
		 session.setAttribute("path", "/Users/apple/apache-tomcat-8.5.15/webapps/mblog1495940635694/blog.html");
	      return new ModelAndView("upload", "command", new Data());
	   }

	   @RequestMapping(value = "/updateData", method = RequestMethod.POST)
	   public String addData(@ModelAttribute("mblog")Data data, 
	   ModelMap model,HttpSession session) throws IOException {
		   String path = (String) session.getAttribute("path"); 	//获得在修改之前path的值
		   DeleteHtml deletehtml = new DeleteHtml();
		   deletehtml.delete(path);  		//删除相应文章，需要传值
		   String username = (String) session.getAttribute("name");
		   CreateHtml createhtml = new CreateHtml();
		   String Filename = path;
		   createhtml.createFile(path);
		   HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		   req.setCharacterEncoding("UTF-8"); 
		   System.out.print(req.getParameter("context"));
	       String BlogBody = req.getParameter("context");
		   WriteHtml writehtml = new WriteHtml();
		   writehtml.write(Filename,BlogBody);
	      model.addAttribute("data", data.getData());
	      return "result";
	   }
}
