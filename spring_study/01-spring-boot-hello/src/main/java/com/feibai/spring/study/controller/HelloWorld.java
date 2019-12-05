package com.feibai.spring.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SpringBoot HelloWorld
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月19日
 *
 */
@Controller
public class HelloWorld {

	@RequestMapping("/hello")
	@ResponseBody // 返回值需要做json转换，所以要加上@ResponseBody,json转换需要依赖jackson的jar包
	public Map<String, Object> showHelloWorld(){
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "HelloWorld");
		return map;
	}

	@RequestMapping(value = "/setCookies", method = RequestMethod.GET)
	@ResponseBody
	public String setCookies(HttpServletResponse response) {
        //HttpServerletRequest 装请求信息类
        //HttpServerletRespionse 装相应信息的类
		Cookie cookie = new Cookie("sessionId", "CookieTestInfo");
		response.addCookie(cookie);
		return "设置cookie成功！";
	}

	// 非注解方式获取cookie中对应的key值
	@RequestMapping(value = "/getCookies", method = RequestMethod.GET)
	@ResponseBody
	public String getCookies(HttpServletRequest request) {
		// HttpServletRequest 装请求信息类
		// HttpServletRespionse 装相应信息的类
		// Cookie cookie=new Cookie("sessionId","CookieTestInfo");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("sessionId")) {
					return cookie.getValue();
				}
			}
		}

		return null;
	}

}
