package com.mblog.blogdata;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mblog.io.CreateHtml;
import com.mblog.io.WriteHtml;

import bean.Blog;
import bean.Label;
import bean.User;
import operation.Update;

@Controller
public class ReceiveData {

   @RequestMapping(value = "/addData", method = RequestMethod.POST)
   public String addData() throws IOException {
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
	   HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	   req.setCharacterEncoding("UTF-8"); 
	   System.out.print(req.getParameter("context"));
       String BlogBody = req.getParameter("context");
	   System.out.print(BlogBody);
	   WriteHtml writehtml = new WriteHtml();
	   writehtml.write(fileName,BlogBody);
       
      return "miao";
   }
}
