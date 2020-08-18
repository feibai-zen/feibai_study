package com.feibai.spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot----spring-boot-devtools使用的是重新部署的方式。对于静态文件的修改可以生效。
 */
@Controller
public class UsersController {

  @RequestMapping("/show")
  public String showPage() {
    System.out.println("ShowPage....bbbb..init....aaa..bbbb..");
    return "index";
  }

  @RequestMapping("/show1")
  public String showPage1() {
    System.out.println("ShowPage......init....aaa..bbbb..");
    return "index";
  }
}
