package com.feibai.study.demos.demos.foundation.mytest;

/**
 * @Author feibai
 **/

public class TestFunctionProgram {

  static {
    System.out.println("static block.");
    tryImplementation(TestFunctionProgram::testFunc);
  }

  public static synchronized void testFunc() {
    System.out.println("testFunc");
    setImplementation();
  }

  private static void setImplementation() {
    System.out.println("setImplementation");
  }

  private static void tryImplementation(Runnable runnable) {
    System.out.println("tryImplementation");
    runnable.run();
  }

  public static void main(String[] args) {
    System.out.println("main");
  }


  public void test_final_variable() {
    final int a;
    a = 10;
  }
}
