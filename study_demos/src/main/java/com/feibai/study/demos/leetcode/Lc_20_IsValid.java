package com.feibai.study.demos.leetcode;

import java.util.Stack;

/**
 * @Author: yuanlong.li
 * @Date: Created in 9:06 下午 2021/3/28
 * @Description: 有效的括号
 * @Company: xmly
 */

public class Lc_20_IsValid {
  public static void main(String[] args) {
    String s = "[{{}]";
    System.out.println(isValid(s));

  }

  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      Character current = s.charAt(i);
      if (stack.size() == 0) {
        //这里需要特别的注意，如果栈中没有元素时候，peek()会抛出异常
        stack.push(current);
        continue;
      }
      Character peek = stack.peek();
      if ((peek.equals('{') && current.equals('}')) || (peek.equals('[') && current.equals(']')) || (peek.equals('(') && current.equals(')'))) {
        stack.pop();
      } else {
        stack.push(current);
      }
    }
    return !(stack.size() > 0);
  }
}
