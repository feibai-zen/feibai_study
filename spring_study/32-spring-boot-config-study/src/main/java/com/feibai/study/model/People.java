package com.feibai.study.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "people")
public class People {
  private String name;
  private Integer age;

  private List<String> address;

  private String phone;

  public String getName() {

    return name;

  }
}


