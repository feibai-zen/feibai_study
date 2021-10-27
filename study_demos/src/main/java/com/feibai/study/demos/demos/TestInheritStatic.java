package com.feibai.study.demos.demos;


class Parent{
  public static String getName(){
   return "Parent";
  }
}


public class TestInheritStatic extends Parent{

  public static String getName(){
    return "son";
  }
  public static void main(String[] args) {

    TestInheritStatic instance = new TestInheritStatic();
    instance.getClass();
    System.out.println(getName());
  }

}


