package com.feibai.designpatterns.study.adapter;

/**
 * @Author: yuanlong.li
 * @Date: Created in 2:39 下午 2021/3/1
 * @Description: 类适配器模式
 */

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