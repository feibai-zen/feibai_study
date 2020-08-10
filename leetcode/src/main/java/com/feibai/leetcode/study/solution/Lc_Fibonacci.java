package com.feibai.leetcode.study.solution;

import java.util.HashMap;
import java.util.Map;

public class Lc_Fibonacci {


  private int constant = 1000000007;

  public static void main(String[] args) {
    System.out.println(solution_1(45));
    System.out.println(solution_2(45));
  }

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
    for (int i = 0; i <= n - 2; i++) {
      sum = first + second;
      first = second;
      second = sum;
    }
    return sum;
  }


  /**
   * 使用map对动态规划方式进行优化
   */
  public int fib(int n) {
    return fib(n, new HashMap());
  }

  private int fib(int n, Map<Integer, Integer> map) {
    if (n < 2) {
      return n;
    }
    if (map.containsKey(n)) {
      return map.get(n);
    }
    int first = fib(n - 1, map) % constant;
    map.put(n - 1, first);
    int second = fib(n - 2, map) % constant;
    map.put(n - 2, second);
    int res = (first + second) % constant;
    map.put(n, res);
    return res;
  }

}
