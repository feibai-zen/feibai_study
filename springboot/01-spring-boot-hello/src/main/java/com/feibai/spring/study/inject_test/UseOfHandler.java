package com.feibai.spring.study.inject_test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseOfHandler implements InitializingBean {

  @Autowired
  private List<Handler> handlerList;//将Handler实现类对象注入


  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("=====================================================");
    System.out.println(handlerList.toString());
  }
}
