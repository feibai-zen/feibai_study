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
    System.out.println(instance.reverse(123));
  }

  //Official solution
  public int reverse(int x) {
    try {
      int result = 0;
      while (x != 0) {
        result = 10 * result + x % 10;
        x = x / 10;
      }
      return result;
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  /**
   * 使用字符串的方式处理
   * <p>
   * 缺点：时间复杂度高，两次遍历数据
   */
  public int reverseByString(int x) {
    if (x == 0) {
      return 0;
    }

    boolean isNegative = false;
    if (x < 0) {
      isNegative = true;
      x = -x;
    }
    String number = String.valueOf(x);
    while (number.endsWith("0")) {
      number = number.substring(0, number.length() - 1);
    }
    StringBuilder sb = new StringBuilder();
    for (int i = number.length() - 1; i >= 0; i--) {
      sb.append(number.charAt(i));
    }
    int result;
    try {
      result = isNegative ? -Integer.valueOf(sb.toString()) : Integer.valueOf(sb.toString());
    } catch (Exception e) {
      result = 0;
    }

    return result;
  }
}
