package com.feibai.spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot----SpringLoader在不重新启动容器的背景下进行的部署
 *
 * 使用这种方式：1)需要在pom文件中配置插件  2)只能对使用springboot:run命令启动的程序能够实现热部署，对于从启动类启动的方式无法生效。
 *
 * 缺点：1）只能对后端java代码起作用，对一些视图html等静态代码无法起作用。
 * 2）使用sprigboot:run命令启动的形式实际上是系统后台以进程的形式进行启动的。需要kill掉。
 *
 */
@Controller
public class UsersController {

	@RequestMapping("/show")
	public String showPage(){
		System.out.println("ShowPage......init");
		return "index";
	}
}
