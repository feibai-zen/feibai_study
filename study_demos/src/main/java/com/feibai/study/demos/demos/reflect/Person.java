package com.feibai.study.demos.demos.reflect;

public class Person {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public void finalize() {
    System.out.println("release resource.");
  }

}

class Person1 {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  private int age;

  public Person1(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String say(String words) {
    return words;
  }

}
