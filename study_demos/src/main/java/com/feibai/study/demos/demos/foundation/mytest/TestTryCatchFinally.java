package com.feibai.study.demos.demos.foundation.mytest;

/**
 * @author leeyuanlong
 */
public class TestTryCatchFinally {
  public static void main(String[] args) {
    System.out.println(fun());
  }

  private static String fun() {
    try {
      return "fun";
    } catch (Exception e) {
      return "catch";
    } finally {
      return "finally";
    }
  }

}