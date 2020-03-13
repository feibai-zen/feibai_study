package com.feibai.spring.study.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 * 
 * ServletContext根目录下放静态资源
 * 
 * src/main/webapp
 * 
 * 目录名称必须要webapp,SpringBoot默认的也会去该目录进行查找
 * 
 * @author feibai
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
