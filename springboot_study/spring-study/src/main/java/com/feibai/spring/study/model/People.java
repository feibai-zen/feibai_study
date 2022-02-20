package com.feibai.spring.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource(value = "classpath:user.properties")
@ConfigurationProperties(prefix = "test.user")
@Data
public class People {
  private String name;
  private Integer age;
  private String phone;
}


