package com.feibai.spring.study.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QuickStart {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return "hello, this is a new start!";
  }
}
