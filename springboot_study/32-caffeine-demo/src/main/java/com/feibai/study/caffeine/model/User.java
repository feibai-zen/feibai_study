package com.feibai.study.caffeine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

  private Integer id;
  private String pwd;
  private String username;
  private Integer age;
}
