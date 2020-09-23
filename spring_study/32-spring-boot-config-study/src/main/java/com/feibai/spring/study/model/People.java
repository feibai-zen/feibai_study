package com.feibai.spring.study.model;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "people")
@Setter
public class People {
  private String name;
  private Integer age;
  private String phone;
}


