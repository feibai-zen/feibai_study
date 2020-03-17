package com.feibai.spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 控制器类
@Controller
@RequestMapping(path = "/user")
public class HelloController {

  /**
   * 入门案例
   *
   * @return
   */
  @RequestMapping(path = "/hello")
  public String sayHello() {
    System.out.println("Hello StringMVC");
    return "success";
  }

  /**
   * RequestMapping注解
   *
   * @return
   */
  @RequestMapping(value = "/testRequestMapping", params = {"username=heihei"}, headers = {"Accept"})
  public String testRequestMapping() {
    System.out.println("测试RequestMapping注解...");
    return "success";
  }


  /**
   * 测试多传参数。进行参数绑定是没有问题
   */
  @RequestMapping(value = "/justTest")
  @ResponseBody
  public String quickTest(@RequestParam Integer a) {
    System.out.println("测试RequestMapping注解...");
    return "success" + a;
  }

}
