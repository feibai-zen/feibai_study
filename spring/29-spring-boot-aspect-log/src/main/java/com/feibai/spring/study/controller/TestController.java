package com.feibai.spring.study.controller;


import com.feibai.spring.study.aspect.AuditLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/test/")
@Controller
public class TestController {

  @AuditLog
  @RequestMapping("first")
  @ResponseBody
  public String firstRequest() {

    return "ok";
  }

}
