package com.feibai.spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringBoot处理异常方式二：定义ExceptionHandler，可以将我们关心的异常跳转到指定页面
 *
 *
 */
@Controller
public class DemoController {
	
	@RequestMapping("/show")
	public String showInfo(){
		String str = null;
		str.length();
		return "index";
	}
	
	@RequestMapping("/show2")
	public String showInfo2(){
		int a = 10/0;
		return "index";
	}
	
	/**
	 * java.lang.ArithmeticException
	 * 该方法需要返回一个ModelAndView：目的是可以让我们封装异常信息以及视图的指定
	 * 参数Exception e:会将产生异常对象注入到方法中
	 */
	@ExceptionHandler(value={java.lang.ArithmeticException.class})
	public ModelAndView arithmeticExceptionHandler(Exception e){
		ModelAndView mv = new ModelAndView();
		mv.addObject("error", e.toString());
		mv.setViewName("error1");
		return mv;
	}
	
	/**
	 * java.lang.NullPointerException
	 * 该方法需要返回一个ModelAndView：目的是可以让我们封装异常信息以及对视图的指定
	 * 参数Exception e:会将产生异常对象注入到方法中
	 */
	@ExceptionHandler(value={java.lang.NullPointerException.class})
	public ModelAndView nullPointerExceptionHandler(Exception e){
		ModelAndView mv = new ModelAndView();
		mv.addObject("error", e.toString());
		mv.setViewName("error2");
		return mv;
	}
	
	
	
}
