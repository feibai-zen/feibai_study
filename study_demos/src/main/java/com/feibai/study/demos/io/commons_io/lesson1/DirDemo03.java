package com.feibai.study.demos.io.commons_io.lesson1;


/**
 * 递归: 方法自己调用自己
 * 递归头: 何时结束递归
 * 递归体: 重复调用
 */
public class DirDemo03 {
  public static void main(String[] args) {
    printTen(1);
  }

  //打印1-10的数
  public static void printTen(int n) {
    if (n > 10) {   //递归头: 结束递归
      return;
    }
    System.out.println(n);
    printTen(n + 1);//递归体:方法自己调用自己
  }
}
