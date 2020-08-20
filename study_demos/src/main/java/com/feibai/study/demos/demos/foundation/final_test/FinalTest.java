package com.feibai.study.demos.demos.foundation.final_test;

public class FinalTest {

  public static void main(String[] args) {

    test_03();
  }

  /**
   * 编译器对final变量优化
   */
  private static void test_01() {
    String a = "hello2";
    final String b = "hello";
    String d = "hello";
    String c = b + 2;
    String e = d + 2;
    System.out.println((a == c));
    System.out.println((a == e));
  }

  /**
   * 编译器无法对final变量优化
   */
  private static void test_02() {
    String a = "hello2";
    final String b = __getHello();
    String c = b + 2;
    System.out.println((a == c));
  }

  private static void test_03() {
    StringBuffer buffer = new StringBuffer("hello");
    __changeValue(buffer);
    System.out.println(buffer);
  }


  private static void test_04(String[] args) {
    StringBuffer buffer = new StringBuffer("hello");
    __changeValue_final(buffer);
    System.out.println(buffer);
  }

  private static String __getHello() {
    return "hello";
  }

  private static void __changeValue(StringBuffer buffer) {
    //buffer重新指向另一个对象
    buffer = new StringBuffer("hi");
    buffer.append("world");
    System.out.println(buffer);
  }

  private static void __changeValue_final(final StringBuffer buffer) {
    //final修饰引用类型的参数，不能再让其指向其他对象，但是对其所指向的内容是可以更改的。
    //buffer = new StringBuffer("hi");
    buffer.append("world");
  }
}
