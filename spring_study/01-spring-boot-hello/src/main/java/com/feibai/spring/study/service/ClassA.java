package com.feibai.spring.study.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("classA")
public class ClassA implements InitializingBean {

  @PostConstruct
  public void function() {
    System.out.println("====================ClassA==============");
  }

  public void init() {
    System.out.println("=============ClassA->init==============");
  }


  public void destroy() {
    System.out.println("=============ClassA->destroy==============");
  }

  @Override
  public void afterPropertiesSet() throws Exception {

  }
}
