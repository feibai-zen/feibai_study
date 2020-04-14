package com.feibai.leetcode.study.solution;


/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 *  示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class Lc_07_ReverseInteger {

  public static void main(String[] args) {
    Lc_07_ReverseInteger instance = new Lc_07_ReverseInteger();
    System.out.println(instance.reverse(123456));
    System.out.println(instance.reverse(-123456));
    System.out.println(instance.reverse(-1234560));
  }

  public int reverse(int x) {
    return 0;
  }
}
