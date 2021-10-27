package com.feibai.study.demos.designpatterns.singleton_my;

public class Singleton_InnerStaticClass {

  private static class InnerClass {
    private static Singleton_InnerStaticClass INSTANCE = new Singleton_InnerStaticClass();
  }

  private Singleton_InnerStaticClass() {
  }

  public static Singleton_InnerStaticClass getInstance() {
    return InnerClass.INSTANCE;
  }


}
