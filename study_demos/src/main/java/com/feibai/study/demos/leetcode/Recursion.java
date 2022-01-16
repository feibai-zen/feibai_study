package com.feibai.study.demos.leetcode;

import javax.management.RuntimeErrorException;

/**
 * @author feibai
 */
public class Recursion {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    System.out.println(start);
    System.out.println(factorial(1000));
    long end = System.currentTimeMillis();
    System.out.println(end);
    System.out.println(end - start);
  }

  public static Long factorial(int n) {
    if (n < 1) {
      throw new RuntimeErrorException(null, "输入异常!");
    }
    Long result = Long.valueOf("1");
    if (n == 1) {
      return result;
    }

    return n * factorial(n - 1);
  }

  public static Long factorial_while(int n) {
    if (n < 1) {
      throw new RuntimeErrorException(null, "");
    }
    Long result = 1L;
    while (n > 1) {
      result *= n;
      n--;
    }

    return result;
  }
}
