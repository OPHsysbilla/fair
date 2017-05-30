package com.demo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bean.Blog;
import bean.ContactBlogLabel;
import bean.Label;
import operation.Search;


//传递文章信息
@Controller
public class PostSingleBlogData {
	@RequestMapping(value = "/postsingleblogdata", method = RequestMethod.GET)
	public String post(HttpSession session){
		Search search = new Search();
		Blog[] blog;
		blog = search.searchBlogByTitle("");
		String path = "maio";
		ContactBlogLabel contactbloglabel= new ContactBlogLabel();
		ContactBlogLabel[] cbl;
		cbl = contactbloglabel.getContactBlogLabelsByID(4, 0);			//
		System.out.print(cbl.length);
		int[] labelid = new int[cbl.length]; 
		for(int i = 0;i<cbl.length;i++){
			System.out.println("miao");
			labelid[i] = cbl[i].getLabelId();
			System.out.println(labelid[i]);
		}
		Label lb = new Label();
		Label[] label = new Label[cbl.length] ;
		for(int i = 0;i<cbl.length;i++){
//			label[i] = lb.getLabelByLabelId(labelid[i]);
			label[i] = lb.getLabelById(labelid[i]);
			System.out.println(label[i].getContent());
		}
		session.setAttribute("label", label);
		session.setAttribute("path", path);		//记录path值之后可能经常用它
		return path;				//这里写返回的地址，你们决定
	}
}
