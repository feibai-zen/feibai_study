package com.feibai.spring.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 核心配置文件是指在resources根目录下的application.properties或application.yml配置文件，读取这两个配置文件的方法有两种
 * <p>
 * 1.使用@Value方式（常用）
 * <p>
 * 2.使用Environment方式
 */
@RestController
public class WebController {
  @Value("${test.msg}")
  private String msg;

  @Autowired
  private Environment env;

  @RequestMapping(value = "index", method = RequestMethod.GET)
  public String index() {
    return "The Way 1 : " + msg;
  }

  @RequestMapping(value = "index2", method = RequestMethod.GET)
  public String index2() {
    return "The Way 2 : " + env.getProperty("test.msg");

  }

}