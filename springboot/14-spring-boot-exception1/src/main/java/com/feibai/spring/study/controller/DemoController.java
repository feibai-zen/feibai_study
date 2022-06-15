package com.feibai.spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot处理异常方式一：自定义错误页面
 * <p>
 * <p>
 * SpringBoot默认处理异常机制：springboot默认的已经提供了一套异常处理机制。一旦程序中出现了异常，springboot会向/error的url发送请求。
 * 在springboot中提供了BasicExceptionController来处理/error请求，然后跳转到默认显示异常的页面来展示异常信息。
 * <p>
 * 在跳转到/error页面的时候会封装一个ErrorProperties对象，并传递给错误页面。这个对象有个属性就叫做exception
 * <p>
 * <p>
 * 缺点：业务异常可能有多种类型，可能需要多种处理方式。自定义错误页面的方式，不管什么异常都会跳转到这个页面。
 */
@Controller
public class DemoController {

  @RequestMapping("/show")
  public String showInfo() {
    String str = null;
    str.length();
    return "index";
  }

  @RequestMapping("/show2")
  public String showInfo2() {
    int a = 10 / 0;
    return "index";
  }


}
