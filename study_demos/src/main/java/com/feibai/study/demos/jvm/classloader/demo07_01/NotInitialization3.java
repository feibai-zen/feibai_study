package com.feibai.study.demos.jvm.classloader.demo07_01;

/**
 * 非主动使用类字段演示￼
 **/
public class NotInitialization3 {

  public static void main(String[] args) {
    System.out.println(ConstClass.HELLOWORLD);
  }
}