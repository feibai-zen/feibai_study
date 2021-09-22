package com.feibai.spring.study.leetcode;

import org.junit.Test;

public class Le_07_ReverseInteger {

  @Test
  public void case0() {
    System.out.println(10 % 3);
    System.out.println(-10 % 3);
    System.out.println(13 % 10);
    System.out.println(-13 % 10);
  }

  @Test
  public void case1() {
    System.out.println(reverse(
        321));
  }

  /**
   * 这道题的关键在于要考虑反转之后的溢出问题
   * <p>
   * 比如：反转之前在int的表示范围内，但是反转之后，就超出了32bit表示范围了
   */
  public int reverse(int x) {
    if (x == 0) {
      return 0;
    }
    boolean isNegative = x < 0;
    int x_abs = Math.abs(x);

    long sum = 0;
    while (x_abs != 0) {
      sum = sum * 10 + x_abs % 10;
      x_abs = x_abs / 10;
    }

    long result = isNegative ? -sum : sum;
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
      return 0;
    } else {
      return (int) result;
    }
  }

}
