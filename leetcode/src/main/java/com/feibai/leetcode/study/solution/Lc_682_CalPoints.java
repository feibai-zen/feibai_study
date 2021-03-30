package com.feibai.leetcode.study.solution;

import java.util.Stack;

/**
 * @Author: yuanlong.li
 * @Date: Created in 9:31 下午 2021/3/28
 * @Description: 棒球比赛
 * @Company: xmly
 */

public class Lc_682_CalPoints {
  public static void main(String[] args) {
    String[] ops = {"5", "-2", "4", "C", "D", "9", "+", "+"};


    System.out.println(calPoints(ops));
  }

  public static int calPoints(String[] ops) {
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < ops.length; i++) {
      String current = ops[i];
      if (current.equals("C") && stack.size() > 0) {
        stack.pop();
      } else if (current.equals("D") && stack.size() > 0) {
        Integer first = stack.pop();
        stack.push(first);
        stack.push(2 * first);
      } else if (current.equals("+") && stack.size() >= 2) {
        Integer first = stack.pop();
        Integer second = stack.pop();
        Integer sum = first + second;
        stack.push(second);
        stack.push(first);
        stack.push(sum);
      } else {
        stack.push(Integer.valueOf(current));
      }
    }
    return sumStack(stack);
  }

  private static int sumStack(Stack<Integer> stack) {
    int sum = 0;
    while (stack.size() > 0) {
      sum += stack.pop();
    }

    return sum;
  }

}
