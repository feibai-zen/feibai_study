package com.feibai.spring.study.controller;


import org.springframework.web.bind.annotation.*;


@RestController
public class QuickStart {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return "hello, this is a new start!";
  }


  @GetMapping("/test")
  public String test01() {

    return "test01";
  }

  @PostMapping("/test")
  public String test02() {

    return "test02";
  }
}
