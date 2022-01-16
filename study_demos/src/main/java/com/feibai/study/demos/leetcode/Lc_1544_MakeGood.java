package com.feibai.study.demos.leetcode;

/**
 * @Author: yuanlong.li
 * @Date: Created in 10:07 下午 2021/3/28
 * @Description: 整理字符串
 * @Company: xmly
 */

import java.util.Stack;

/**
 * 输入：s = "abBAcC"
 * 输出：""
 * 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如：
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 */
public class Lc_1544_MakeGood {
  public static void main(String[] args) {
    String s = "leEeetcode";
    System.out.println(makeGood(s));
  }

  public static String makeGood(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      Character current = s.charAt(i);
      if (stack.size() > 0) {
        Character first = stack.pop();
        if (Math.abs(current - first) == 32) {
          continue;
        } else {
          stack.push(first);
          stack.push(current);
        }
      } else {
        stack.push(current);
      }
    }

    return mergeStack(stack);
  }

  private static String mergeStack(Stack<Character> stack) {
    StringBuilder sb = new StringBuilder();
    while (stack.size() > 0) {
      sb.append(stack.pop());
    }
    return sb.reverse().toString();
  }

}
