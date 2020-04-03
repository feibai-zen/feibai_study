package com.feibai.leetcode.study.solution;

import java.util.ArrayList;
import java.util.List;

public class YangHuiTriangle {

  public static void main(String[] args) {
    System.out.println(generate(30));
  }

  public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    for (int i = 0; i < numRows; i++) {
      List<Integer> tmp = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i || i == 0) {
          tmp.add(1);
        } else {
          tmp.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
        }
      }
      result.add((ArrayList<Integer>) tmp);
    }

    return result;
  }

  // 递归的方式求数值
  private static Integer value(int index, int rowNo) {
    if (index == 1 || index == rowNo) {
      return 1;
    }

    return value(index - 1, rowNo - 1) + value(index, rowNo - 1);
  }

}
