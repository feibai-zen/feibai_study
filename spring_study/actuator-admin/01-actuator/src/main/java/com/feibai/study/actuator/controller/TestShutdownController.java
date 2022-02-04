package com.feibai.study.actuator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestShutdownController {

  @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
  public String testShutdown() {
    return "success";
  }
}
