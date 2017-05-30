package com.mblog.blogdata;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mblog.io.CreateHtml;
import com.mblog.io.DeleteHtml;
import com.mblog.io.WriteHtml;

@Controller
public class UpdateTag {
	@RequestMapping(value = "/updatetag", method = RequestMethod.GET)		//这是小写
	   public ModelAndView provide_data(HttpSession	session) {
	      return new ModelAndView("upload", "command", new Data());
	   }

	   @RequestMapping(value = "/updateTag", method = RequestMethod.POST)		//这是大写
	   public String addData(@ModelAttribute("mblog")Data data, 
	   ModelMap model,HttpSession session) throws IOException {
		   String path = (String) session.getAttribute("path"); 	//获得在修改之前path的值
		   DeleteHtml deletehtml = new DeleteHtml();
		   deletehtml.delete(path);  		//删除相应文章，需要传值
		   String username = (String) session.getAttribute("name");
		   CreateHtml createhtml = new CreateHtml();
		   String dirName = "/Users/apple/apache-tomcat-8.5.15/webapps/mblog/";
		   dirName = dirName+username;
		   createhtml.createDir(dirName);
		   String Filename = dirName+"/"+"miao.html";
		   createhtml.createFile(Filename);
		   System.out.print(data.getData());
		   WriteHtml writehtml = new WriteHtml();
		   writehtml.write(Filename,data.getData());
	      model.addAttribute("data", data.getData());
	      return "result";
	   }
}
