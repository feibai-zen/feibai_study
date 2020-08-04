package com.feibai.spring.study.service;


import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("classB")
@DependsOn({"classA"})
public class ClassB {

  @PostConstruct
  public void function() {
    System.out.println("============ClassB===========");
  }

  public void init() {
    System.out.println("=============ClassB->init==============");
  }


  public void destroy() {
    System.out.println("=============ClassB->destroy==============");
  }
}
