package com.feibai.spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * SpringBoot处理异常方式二：定义ExceptionHandler，可以将我们关心的异常跳转到指定页面
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
