package com.mblog.blogdata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mblog.io.DeleteHtml;

@Controller
public class DeleteData {
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(){
		DeleteHtml deletehtml = new DeleteHtml();
		deletehtml.delete("/Users/apple/apache-tomcat-8.5.15/webapps/mblog/miao/miao.html");
		return "result";
	}
}
