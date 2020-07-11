package com.feibai.study.demos.beans;

import lombok.Data;

@Data
public class Birthday {

  private String birthday;

  public Birthday() {
  }

  public Birthday(String birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return this.birthday;
  }
}