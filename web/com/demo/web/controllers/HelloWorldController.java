package com.demo.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.web.auth.AuthPassport;
import com.demo.web.models.AccountModel;
 
@Controller
@RequestMapping(value = "/helloworld")
public class HelloWorldController { 
	@AuthPassport
	@RequestMapping(value={"/index"})
	public String index(HttpServletRequest request, Model model){
	    model.addAttribute("message", "Hello springmvc");
	    return "/fair/account/login";
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