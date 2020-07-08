package com.feibai.study.demos.beans;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee implements Comparable<Employee> {
  private Integer id;
  private String name;
  private double salary;


  @Override
  public int compareTo(Employee o) {
    if (o == this) {
      return 0;
    }
    if (this.id > o.id) {
      return 1;
    }
    if (o.id < this.id) {
      return -1;
    }
    return -1;
  }

}
