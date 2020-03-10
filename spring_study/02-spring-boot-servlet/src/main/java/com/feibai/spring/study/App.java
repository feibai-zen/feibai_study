package com.feibai.spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * demo演示了三种servlet整合方式：
 * 1）web.xml的方式
 * 2）@WebServlet方式
 * 3）@SpringBootApplication
 */

/**
 * SpringBoot整合Servlet方式二
 */
@SpringBootApplication
@ServletComponentScan //在springBoot启动时会扫描@WebServlet，并将该类实例化
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
