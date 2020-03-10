package com.feibai.spring.study;

import com.feibai.spring.study.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * demo演示了三种servlet整合方式：
 * 1）web.xml的方式
 * 2）@WebServlet方式
 * 3）@SpringBootApplication
 */

/**
 * SpringBoot整合Servlet方式三
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
