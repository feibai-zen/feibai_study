package com.feibai.spring.study.contorller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feibai.spring.study.pojo.Users;

/**
 * SpringBoot 表单数据校验
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月19日
 *
 */
@Controller
public class UsersController {
	/**
	 * 
	 * 解决异常的方式。 
	 * 1）可以在跳转页面的方法中注入一个Users对象。注意：springmvc会将对象放入到Model中传递。
	 * key的名称会使用该对象的驼峰命名规则来作为key，参数的变量名需要与对象名称相同，将首字母小写。
	 * 
	 * 2）如果想为传递的对象更改名称，可以使用@ModelAttribute("aa"),这表示当前传递的对象的key为aa。
	 * 那么我们在页面中获取该对象的key也需要修改为aa
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping("/addUser")
	public String showPage(@ModelAttribute("aa") Users users) {
		return "add";
	}

	/**
	 * 完成用户添加
	 */

	// @Valid 开启对Users对象的数据校验。
	// BindingResult:封装了校验的结果(包括校验的提示信息)
	// @.当对users校验不合法的时候，User对象会被SpringMVC拦截后放到ModelAndView里。
	// 其中model.addAttribute("key",Object)的key会采用对象的驼峰命名作为key，即users.
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("aa") @Valid Users users, BindingResult result) {
		if (result.hasErrors()) {// 判断是否含有校验错误
			return "add";
		}
		System.out.println(users);
		return "ok";
	}
}
