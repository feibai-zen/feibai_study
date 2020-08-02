package com.feibai.study.demos.io.commons_io.lesson3;


//javabean 封装数据
class Employee implements java.io.Serializable {
  private transient String name; //该数据不需要序列化
  private double salary;

  public Employee() {
  }

  public Employee(String name, double salary) {
    this.name = name;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

}