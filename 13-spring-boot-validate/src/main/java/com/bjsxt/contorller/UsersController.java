package com.bjsxt.contorller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bjsxt.pojo.Users;

/**
 * SpringBoot 表单数据校验
 *
 *
 */
@Controller
public class UsersController {
	/**
	 * 
	 * 如果想为传递的对象更改名称，可以使用@ModelAttribute("aa")这表示当前传递的对象的key为aa。
	 * 那么我们在页面中获取该对象的key也需要修改为aa
	 * @param users
	 * @return
	 */
	@RequestMapping("/addUser")
	public String showPage(@ModelAttribute("aa") Users users){
		return "add";
	}
	
	/**
	 * 完成用户添加
	 *@Valid 开启对Users对象的数据校验
	 *BindingResult:封装了校验的结果
	 */
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("aa") @Valid Users users,BindingResult result){
		if(result.hasErrors()){
			return "add";
		}
		System.out.println(users);
		return "ok";
	}
}
