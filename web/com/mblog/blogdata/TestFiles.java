package com.mblog.blogdata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestFiles {
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public ModelAndView provide_data() {
		return new ModelAndView("upload_file");
	}
	
}
