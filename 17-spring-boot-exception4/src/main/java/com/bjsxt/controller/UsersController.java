package com.bjsxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

	@RequestMapping("/showUsers")
	public String showUser(){
		String str = null;
		str.length();
		return "index";
	}
	
	@RequestMapping("/showUsers2")
	public String showUser2(){
		int a = 100/0;
		return "index";
	}
}
