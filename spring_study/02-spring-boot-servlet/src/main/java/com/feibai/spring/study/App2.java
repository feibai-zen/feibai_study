package com.feibai.spring.study;

import com.feibai.spring.study.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * SpringBoot整合Servlet方式二
 *
 *
 */
@SpringBootApplication
public class App2 {

	public static void main(String[] args) {
		SpringApplication.run(App2.class, args);
	}
	
	@Bean // 通过方法完成Servlet组件的注册
	public ServletRegistrationBean getServletRegistrationBean() {// 方法名称无所谓
		ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());
		bean.addUrlMappings("/second");
		return bean;
	}
}
