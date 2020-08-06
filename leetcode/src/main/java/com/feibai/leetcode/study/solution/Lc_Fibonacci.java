package com.feibai.leetcode.study.solution;

public class Lc_Fibonacci {

  /**
   * 使用递归,leetcode会超时
   */
  public static int solution_1(int n) {
    if (n <= 1) {
      return n;
    }
    return solution_1(n - 1) + solution_1(n - 2);
  }

  /**
   * 使用循环
   */
  public static int solution_2(int n) {
    if (n <= 1) {
      return n;
    }

    int first = 0;
    int second = 1;
    int sum = 0;
    for (int i = 0; i < n - 1; i++) {
      sum = first + second;
      first = second;
      second = sum;
    }
    return sum;
  }

}
