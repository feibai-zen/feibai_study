package com.bjsxt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
