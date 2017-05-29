package com.mblog.blogdata;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mblog.io.CreateHtml;
import com.mblog.io.WriteHtml;

import bean.Blog;
import bean.Label;
import bean.User;
import operation.Update;

@Controller
@SessionAttributes("user")
public class ReceiveData {

   @RequestMapping(value = "/upload", method = RequestMethod.GET)
   public ModelAndView provide_data(HttpSession	session) {
	   session.setAttribute("name", "miao");
	   return new ModelAndView("upload", "command", new Data());
   }

   @RequestMapping(value = "/addData", method = RequestMethod.POST)
   public String addData(@ModelAttribute("mblog")Data data, 
   ModelMap model,HttpSession session) throws IOException {
	   String username = (String) session.getAttribute("name");
	   Blog blog = new Blog();
	   blog.setTitle("系统测试-第十篇博客");
	   blog.setAbstracts("第十篇博客的摘要");
	   blog.setUserID(User.getUserByName("Tom").getId());
	   Label[] labels = new Label[3];
	   labels[0] = new Label(".NET");
	   labels[1] = new Label("开发技术");
	   labels[2] = new Label("上海大学暑期大作业");
	   String dirPath = Update.addBlog(blog, labels);
	   
	   CreateHtml createhtml = new CreateHtml();
	   createhtml.createDir(dirPath);
	   String fileName = dirPath + "/blog.html";
	   createhtml.createFile(fileName);
	   System.out.print(data.getData());
	   WriteHtml writehtml = new WriteHtml();
	   writehtml.write(fileName,data.getData());
       model.addAttribute("data", data.getData());
       return "result";
   }
}
