package com.feibai.study.demos.designpatterns.adapter;

public class ClassAdapter extends Adaptee implements Target {
  @Override
  public void method2() {
    System.out.println("method 2");
  }
}

// 测试
class ClassAdapterTest {
  public static void main(String[] args) {
    ClassAdapter adapter = new ClassAdapter();
    adapter.method1();
    adapter.method2();
  }
}