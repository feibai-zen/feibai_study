package com.bjsxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot处理异常方式四： 通过SimpleMappingExceptionResolver做全局异常处理
 * 
 * 缺点：无法在视图跳转是向页面传递异常对象
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月20日
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
